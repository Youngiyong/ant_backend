package com.ant.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ant.mapper.springboot.UserMapper;
import com.ant.vo.BoardVO;
import com.ant.vo.KakaoUserVO;


@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
    UserMapper userMapper;
	
	@Override
	public void userJoin(KakaoUserVO vo) throws Exception {
		System.out.println("서비스");
		userMapper.kakaoinsertUser(vo);
		
	}
	@Override
	public KakaoUserVO kakaofetchUserByID(String kakaoid) throws Exception {
		KakaoUserVO fetchUser = userMapper.kakaofetchUserByID(kakaoid);
		return fetchUser;
	}
	@Override
	public List<KakaoUserVO> UserListGet() throws Exception {
		List<KakaoUserVO> userlist = userMapper.UserListGet();
		return userlist;
	}
	@Override
	public int deleteUser(int id) throws Exception {
		int result = userMapper.deleteUser(id);
		return result;
	}
	@Override
	public int updateUser(KakaoUserVO vo) throws Exception {
		int result = userMapper.updateUser(vo);
		return result;
	}
	@Override
	public int UserTotalCountGet() throws Exception {
		int totalcount = userMapper.UserTotalCountGet();
		return totalcount;
	}
	@Override
	public int UserSubCountGet() throws Exception {
		int subcount = userMapper.UserSubCountGet();
		return subcount;
	}
	@Override
	public int editUserLikedComment(String userid, String comment_id) throws Exception {
		
		String commentlist = userMapper.getUserComment(userid);
		System.out.println("커맨드 : "+commentlist);
		List commentlisttemp = new ArrayList(Arrays.asList(commentlist.split(","))) ;
		System.out.println("사이즈"+commentlisttemp.size());
		System.out.println("첫번쨰"+commentlisttemp.get(0));
		System.out.println("투스트링"+commentlisttemp.toString());
		System.out.println("투스트링"+commentlisttemp.getClass().getName());
		
		
		System.out.println("어레이안에있는지 테스트"+commentlisttemp.contains(comment_id));
		
		if(commentlisttemp.contains(comment_id)) { //이미 있으면 삭제
			System.out.println("이미있다");
			commentlisttemp.remove(commentlisttemp.indexOf(comment_id));
		}else {   // 리스트에 없으면 
			System.out.println("리스트에 없다.");
			commentlisttemp.add(comment_id);	
		}
		
		System.out.println("투스트링"+commentlisttemp.toString());
		System.out.println("사이즈"+commentlisttemp.size());
		String asdasdasd = String.valueOf(commentlisttemp);
		System.out.println("그냥치기1"+asdasdasd);
		String temptemp = asdasdasd.substring(1, asdasdasd.length()-1 );
		System.out.println("그냥치기2"+temptemp.getClass().getName());
		System.out.println("그냥치기3"+temptemp);
		String temptemptemp = temptemp.replaceAll(" ", "");
		System.out.println("그냥치기4" + temptemptemp);
		System.out.println("그냥치기4" + temptemptemp.getClass().getName());
		KakaoUserVO user = new KakaoUserVO();
		user.setUserid(Integer.valueOf(userid));
		user.setCommentlist(temptemptemp);
		userMapper.editUserLikedCommentChange(user);
		
		
		
//		int result = userMapper.editUserLikedComment(userid,comment_id);
		return 0;
	}
	@Override
	public List<String> getUserLikedCommentList(String userid) throws Exception {
		String commentlist = userMapper.getUserComment(userid);
		List commentlisttemp = new ArrayList(Arrays.asList(commentlist.split(","))) ;
		// TODO Auto-generated method stub
		return commentlisttemp;
	}
	@Override
	public List<String> fetchUserLikedBoardList(String userid) throws Exception {
		List<String> commentlist = userMapper.fetchUserLikedBoardList(userid);
		return commentlist;
	}
	
	//마이페이지-회원정보보기
	public KakaoUserVO profileShow(int id) {
		System.out.println("마이페이지-회원정보보기");
		return userMapper.profileShow(id);
	}
	
	//컨트롤러-마이페이지-회원정보수정
	public void profileEdit(KakaoUserVO updateUser) {
		userMapper.profileEdit(updateUser);	
	}
	
	//컨트롤러-마이페이지-나의 게시글
	public List<BoardVO> boardShow(int id) {
		return userMapper.boardShow(id);
	}
	
	//컨트롤러-마이페이지-비밀번호수정
	public void passwordEdit(KakaoUserVO updateUser) {
		userMapper.passwordEdit(updateUser);	
	}

	
}
