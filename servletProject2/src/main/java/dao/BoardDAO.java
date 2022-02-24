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
			String sql = "select * from board order by bidx";
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
}
