package com.ant.service;

import java.util.List;

import com.ant.vo.BoardVO;
import com.ant.vo.KakaoUserVO;

public interface UserService {

	public void userJoin(KakaoUserVO vo) throws Exception;
	public int UserTotalCountGet() throws Exception;
	public int UserSubCountGet() throws Exception;
	public KakaoUserVO kakaofetchUserByID(String kakaoid) throws Exception;
	public List<KakaoUserVO> UserListGet() throws Exception;
	public int deleteUser(int id) throws Exception;
	public int updateUser(KakaoUserVO vo) throws Exception;
	public int editUserLikedComment(String userid,String comment_id) throws Exception;
	public List<String> getUserLikedCommentList(String userid) throws Exception;
	public List<String> fetchUserLikedBoardList(String userid) throws Exception;
	//마이페이지-회원정보보기
	public KakaoUserVO profileShow(int id);
	
	//컨트롤러-마이페이지-회원정보수정
	public void profileEdit(KakaoUserVO updateUser);
	
	//컨트롤러-마이페이지-나의 게시글
	public List<BoardVO> boardShow(int id);
	
	//컨트롤러-마이페이지-비밀번호 수정
	public void passwordEdit(KakaoUserVO updateUser);
}
