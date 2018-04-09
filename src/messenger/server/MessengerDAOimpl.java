package messenger.server;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import messenger.common.MemberDTO;

public class MessengerDAOimpl implements MessengerDAO{
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	public MessengerDAOimpl() throws SQLException {
		conn = DataBaseUtil.getConnection();
	}
	@Override
	public int checkID(String id) {
		int cnt=0;
			try {
				StringBuffer sql = new StringBuffer();
				sql.append("select id from member where id =?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				while(rs.next()) {
				cnt++;
				}
			}catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DataBaseUtil.close(conn, pstmt, rs);
			}
			
		return cnt;
		
	}
	@Override
	public int insertMember(MemberDTO member) {
		int cnt = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("insert into member ")
			.append("(id, passwd, name, alias, loc, sex, birth, phone) ")
			.append("values (?,?,?,?,?,?,?,?)");
		try {
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getAlias());
			pstmt.setString(5, member.getLoc());
			pstmt.setString(6, member.getSex());
			pstmt.setString(7, member.getBirth());
			pstmt.setString(8, member.getPhone());
			
			cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				conn.commit();
			} else {
				throw new RecordNotFoundException();
			}
		}catch (SQLException | RecordNotFoundException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			DataBaseUtil.close(conn, pstmt, rs);
			
		}
		return cnt;
	}
	@Override
	public int updateMember(MemberDTO member) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int deleteMember(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String findId(String name, String birth, String phone) {
		StringBuffer sql = new StringBuffer();
		String id = null;
		sql.append("select id from member where name=? and birth=? and phone=?");
		try {
			pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			pstmt.setString(2, birth);
			pstmt.setString(3, phone);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				id = rs.getString("id");
			}
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		return id;
	}
	@Override
	public String findPasswd(String id, String birth, String phone) {
		StringBuffer sql = new StringBuffer();
		String pw =null;
		sql.append("select passwd from member where id=? and birth=? and phone=?");
		try {
			pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, birth);
			pstmt.setString(3, phone);
			rs=pstmt.executeQuery();
			while(rs.next()) {
			pw = rs.getString("passwd");
			}
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		return pw;
	}
	@Override
	public int Login(String id, String pass) {
		int cnt=0;
		StringBuffer sql = new StringBuffer();
		sql.append("select id, passwd from member where id = ? and passwd = ?");
		try {
			pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);	
			pstmt.setString(2, pass);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				cnt++;
			}
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		return cnt;
	}
	@Override
	public int checkPhone(String phone) {
		int cnt=0;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select phone from member where phone =?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, phone);
			rs = pstmt.executeQuery();
			while(rs.next()) {
			cnt++;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.close(conn, pstmt, rs);
		}
		
	return cnt;
	
	}
	@Override
	public MemberDTO getInfo(String id) {
		MemberDTO member = new MemberDTO();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("Select alias, birth, phone,loc,passwd from member where id = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				member.setAlias(rs.getString("alias"));
				member.setBirth(rs.getString("birth"));
				member.setPhone(rs.getString("phone"));
				member.setLoc(rs.getString("loc"));
				member.setPasswd(rs.getString("passwd"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
	public int changeInfo(String id, String passwd, String alias, String loc, String birth, String phone) {
		int cnt = 0;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("update member set passwd=? ,alias= ?, loc= ?,birth=?, phone=? where id=?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, passwd);
			pstmt.setString(2, alias);
			pstmt.setString(3, loc);
			pstmt.setString(4, birth);
			pstmt.setString(5, phone);
			pstmt.setString(6, id);
			cnt = pstmt.executeUpdate();
		if (cnt == 1) {
			conn.commit();
		} else {
			throw new RecordNotFoundException();
		}}catch (SQLException | RecordNotFoundException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();
	}finally {
		DataBaseUtil.close(conn, pstmt, rs);
		
	}
	return cnt;
}
}
