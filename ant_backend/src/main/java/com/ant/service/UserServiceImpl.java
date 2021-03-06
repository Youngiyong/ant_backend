package com.ant.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ant.mapper.springboot.UserMapper;
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
//		List<List<String>> commentlisttemp = new ArrayList<List>(Arrays.asList(commentlist.split("/"))) ;
//		System.out.println(commentlisttemp.size());
//		System.out.println(commentlisttemp.get(0));
//		System.out.println(commentlisttemp.toString());
//		commentlisttemp.add(comment_id);
//		
//		System.out.println(commentlisttemp.toString());
//		int result = userMapper.editUserLikedComment(userid,comment_id);
		return 0;
	}

	
}
