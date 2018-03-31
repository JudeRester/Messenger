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
	public String checkID(String id) {
		MemberDTO member = new MemberDTO();
			try {

				StringBuffer sql = new StringBuffer();
				sql.append("select id ")
				   .append("from member ")
				   .append("where id =?");
		
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				rs.next();
				member.setId(rs.getString("id"));
				
			}catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DataBaseUtil.close(conn, pstmt, rs);
			}
			
		return id;
		
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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String findPasswd(String id, String birth, String phone) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
