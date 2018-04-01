package messenger.server;

import java.util.ArrayList;

import messenger.common.MemberDTO;

public interface MessengerDAO {
	
	// 회원조회
		int checkID(String id);

		// 회원등록
		int insertMember(MemberDTO member);
		
		// 회원수정
		int updateMember(MemberDTO member);
		
		// 회원삭제
		int deleteMember(String id);
		
		// 아이디찾기
		String findId(String name, String birth, String phone);

		// 비밀번호찾기
		String findPasswd(String id, String birth, String phone);

		int Login(String id, String pass);
		
		// 친구등록
//		int insertFriend(FriendsDTO friend);
//		
//		// 친구삭제
//		int deleteFriend(String myId, String friendId);
//		
//		// 친구찾기
//		FriendsDTO getFriend(String myId, String friendId);
//
//		// 친구들찾기
//		ArrayList<FriendsDTO> getFriends(String id);

}
