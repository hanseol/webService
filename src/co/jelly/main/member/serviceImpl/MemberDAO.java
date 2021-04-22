package co.jelly.main.member.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.jelly.main.common.DataSource;
import co.jelly.main.member.service.MemberService;
import co.jelly.main.member.vo.MemberVO;

public class MemberDAO implements MemberService {
	private DataSource dataSource = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<MemberVO> memberSelectList() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO vo;
		String sql = "select * from member";
		
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new MemberVO();
				vo.setMemberId(rs.getString("memberid"));
				vo.setMemberName(rs.getString("membername"));
				vo.setMemberPassword(rs.getString("memberpassword"));
				vo.setMemberAddress(rs.getString("memberaddress"));
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}

	@Override
	public MemberVO memberSelect(MemberVO vo) {
		return null;
	}

	@Override
	public int memberInsert(MemberVO vo) {
		int cnt=0;
		String sql = "insert into member values(?,?,?,?)";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,vo.getMemberId());
			psmt.setString(2,vo.getMemberName());
			psmt.setString(3,vo.getMemberPassword());
			psmt.setString(4,vo.getMemberAddress());
			cnt = psmt.executeUpdate();
		}catch(SQLException e){
			
		}finally {
			close();
		}
		return cnt;
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		return 0;
	}

	@Override
	public int memberDelete(MemberVO vo) {
		int n = 0;
		String sql = "delete from member where memberid = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			n = psmt.executeUpdate();
		}catch(SQLException e) {
			
		}finally {
			close();
		}
		return n;
	}

	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
