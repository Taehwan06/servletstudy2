package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBManager;
import vo.BoardVO;
import vo.MemberVO;

public class MemberDAO {

	public ArrayList<MemberVO> selectAll() {
		
		ArrayList<MemberVO> alist = new ArrayList<MemberVO>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			String sql = "select * from member order by midx desc";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("memberid"));
				vo.setName(rs.getString("membername"));
				vo.setMidx(rs.getInt("midx"));
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
