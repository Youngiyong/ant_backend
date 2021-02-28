package com.ant.service;

import java.util.List;

import com.ant.vo.BoardVO;
import com.ant.vo.KakaoUserVO;

public interface UserService {

	public void userJoin(KakaoUserVO vo) throws Exception;
	public KakaoUserVO kakaofetchUserByID(String kakaoid) throws Exception;
	public List<KakaoUserVO> UserListGet() throws Exception;
	public int deleteUser(int id) throws Exception;
}
