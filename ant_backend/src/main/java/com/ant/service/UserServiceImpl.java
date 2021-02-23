package com.ant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ant.mapper.UserMapper;
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

	
}
