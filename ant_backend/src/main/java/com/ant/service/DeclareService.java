package com.ant.service;

import java.util.List;

import com.ant.vo.DeclaredVO;

public interface DeclareService {

//	public List<CommentVO> CommentListGet() throws Exception;
//	public List<CommentVO> fetchCommentsByBoardID(int boardid) throws Exception;
//	public CommentVO fetchcommentByID(int commentid) throws Exception;
//	public int updateComment(CommentVO comment);
	public int insertDeclare(DeclaredVO declare);
//	public int deleteComment(int commentid);
	public List<DeclaredVO> fetchDeclaredByID(String userid);
}
