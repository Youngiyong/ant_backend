package com.ant.service;

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

	
}
