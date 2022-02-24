package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBManager;
import vo.BoardVO;

public class BoardDAO {

	public ArrayList<BoardVO> selectAll(){
		
		ArrayList<BoardVO> alist = new ArrayList<BoardVO>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			String sql = "select * from board order by bidx desc";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBidx(rs.getInt("bidx"));
				vo.setHit(rs.getInt("bhit"));
				vo.setTitle(rs.getString("bsubject"));
				vo.setWdate(rs.getString("bwdate"));
				vo.setWriter(rs.getString("bwriter"));
				alist.add(vo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(psmt, conn, rs);
		}
		
		return alist;
	}
	
	public BoardVO selectOne(String bidx) {
		
		BoardVO vo = new BoardVO();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			String sql = "select * from board where bidx=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, bidx);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo.setBidx(rs.getInt("bidx"));
				vo.setHit(rs.getInt("bhit"));
				vo.setTitle(rs.getString("bsubject"));
				vo.setWdate(rs.getString("bwdate"));
				vo.setWriter(rs.getString("bwriter"));
				vo.setContent(rs.getString("bcontent"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(psmt, conn, rs);
		}
		
		return vo;
	}
	
	public void update(BoardVO vo) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBManager.getConnection();
			String sql = "update board set bsubject=?, bcontent=? where bidx=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setInt(3, vo.getBidx());
			psmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(psmt, conn);
		}
	}
	
	public void insert(BoardVO vo) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBManager.getConnection();
			String sql = "insert into board(bidx,bsubject,bcontent,bwriter,midx) values(bidx_seq.nextval,?,?,?,3)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setString(3, vo.getWriter());
			psmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(psmt, conn);
		}
	}
	
		public void delete(String bidx) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBManager.getConnection();
			String sql = "delete from board where bidx = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, bidx);
			psmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(psmt, conn);
		}
	}
	
}
