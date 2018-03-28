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
	MessengerDAOimpl() throws SQLException {
		conn = DataBaseUtil.getConnection();
	}
	@Override
	public MemberDTO getMember(String id) {
		MemberDTO member = new MemberDTO();
			try {

				StringBuffer sql = new StringBuffer();
				sql.append("select id,passwd,name,alias,loc,sex,birth,phone ")
				   .append("from member ")
				   .append("where id = ?");
		
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				rs.next();
				member.setId(rs.getString("id"));
				member.setPasswd(rs.getString("passwd"));
				member.setName(rs.getString("name"));
				member.setAlias(rs.getString("alias"));
				member.setLoc(rs.getString("loc"));
				member.setSex(rs.getString("sex"));
				member.setBirth(rs.getString("birth"));
				member.setPhone(rs.getString("phone"));	
			}catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DataBaseUtil.close(conn, pstmt, rs);
			}
			System.out.println(member);
		return member;
		
	}
	@Override
	public int insertMember(MemberDTO member) {
		int cnt = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("insert into member ")
			.append("id, passwd, name, alias, loc, sex, birth, phone) ")
			.append("values (?,?,?,?,?,?,?,?)");
		try {
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, member.getId());
		}catch (SQLException e) {
			DataBaseUtil.printSQLException(e, 
					this.getClass().getName()+
					": int insertMember(MemberDTO member)");
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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String findPasswd(String id, String birth, String phone) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
