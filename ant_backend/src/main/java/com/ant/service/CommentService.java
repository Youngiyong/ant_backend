package com.ant.service;

import java.util.List;

import com.ant.vo.BoardVO;
import com.ant.vo.CommentVO;
import com.ant.vo.KakaoUserVO;

public interface CommentService {

	public List<CommentVO> CommentListGet() throws Exception;
	public List<CommentVO> fetchCommentsByBoardID(int boardid) throws Exception;
	public CommentVO fetchcommentByID(int commentid) throws Exception;
	public int updateComment(CommentVO comment);
	public int insertComment(CommentVO comment);
	public int deleteComment(int commentid);
}
