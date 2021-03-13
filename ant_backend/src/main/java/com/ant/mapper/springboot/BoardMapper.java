package com.ant.mapper.springboot;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ant.vo.BoardVO;
import com.ant.vo.KakaoUserVO;
import com.ant.vo.UserVO;
 
@Mapper
public interface BoardMapper {
 
    public List<BoardVO> boardList();
    public List<BoardVO> boardListLiked();
    public List<BoardVO> boardListGetSave();
    public List<BoardVO> fetchTopMainBoards();
    
    public int insertBoard(BoardVO board);
    
    public void BoardViewCount(int id);
    public BoardVO fetchBoardByID(int id);
    public int updateBoard(BoardVO board);
    
    public int addUserLikeBoard(BoardVO board);
    public int deleteUserlikeBoard(Map map);
    public int fetchUserlikeBoard(Map map);
    
    public List<String> fetchUserSavedBoardListCheck(int userid);
    public int addSaveddUserBoard(BoardVO board);
    public int deleteSaveddUserBoard(BoardVO board);
    
    
    public List<BoardVO> fetchUserSavedBoardList(int userid);
    public List<BoardVO> fetchSavedUserBoardLiked(int userid);
    public int deleteBoard(int boardid);
////    public KakaoUserVO fetchUserByID(int id);
//    public KakaoUserVO kakaofetchUserByID(String id);
//    public void updateUser(KakaoUserVO user);
//    public void insertUser(KakaoUserVO user);
//    public void kakaoinsertUser(KakaoUserVO kakaouser);
//    public void deleteUser(int id);
    
}