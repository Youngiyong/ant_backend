package com.ant.mapper;

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
    
}