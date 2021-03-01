package com.ant.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ant.vo.BoardVO;
import com.ant.vo.CommentVO;
import com.ant.vo.KakaoUserVO;
import com.ant.vo.UserVO;
 
@Mapper
public interface CommentMapper {
 
    public List<CommentVO> commentList();
    public List<CommentVO> fetchCommentsByBoardID(int boardid);
    public CommentVO fetchcommentByID(int commentid);
    
    public int updateComment(CommentVO comment);

    public int deleteComment(int commentid);
    
}