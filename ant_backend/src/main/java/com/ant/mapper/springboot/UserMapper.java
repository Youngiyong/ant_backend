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
}