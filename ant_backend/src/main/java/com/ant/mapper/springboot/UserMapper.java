package com.ant.mapper.springboot;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.ant.vo.BoardVO;
import com.ant.vo.KakaoUserVO;
import com.ant.vo.UserVO;
 
@Mapper
public interface UserMapper {
 
    public List<KakaoUserVO> UserListGet();
//    public KakaoUserVO fetchUserByID(int id);
    public KakaoUserVO kakaofetchUserByID(String id);
    public void insertUser(KakaoUserVO user);
    public void kakaoinsertUser(KakaoUserVO kakaouser);
    public int deleteUser(int id);
    public int updateUser(KakaoUserVO user);
    public int UserTotalCountGet();
    public int UserSubCountGet();
    public String getUserComment(String userid);
    public int editUserLikedComment(String userid, String comment_id);
    public int editUserLikedCommentChange(KakaoUserVO user);
    public List<String> fetchUserLikedBoardList(String userid) throws Exception;
    //마이페이지-회원정보 보기
	public KakaoUserVO profileShow(int id);
	
	//마이페이지-회원정보 수정
	public void profileEdit(KakaoUserVO updateUser);
	
	//컨트롤러-마이페이지-나의 게시글
	public List<BoardVO> boardShow(int id);
	
	//컨트롤러-마이페이지-비밀번호 수정
	public void passwordEdit(KakaoUserVO updateUser);
}