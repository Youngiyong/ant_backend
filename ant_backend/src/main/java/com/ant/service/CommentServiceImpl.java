package com.ant.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ant.mapper.springboot.BoardMapper;
import com.ant.mapper.springboot.CommentMapper;
import com.ant.mapper.springboot.UserMapper;
import com.ant.vo.BoardVO;
import com.ant.vo.CommentVO;
import com.ant.vo.KakaoUserVO;


@Service("CommentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentMapper commentMapper;

	@Override
	public List<CommentVO> CommentListGet() throws Exception {
		List<CommentVO> commentlist = commentMapper.commentList();
		return commentlist;
	}
	@Override
	public List<CommentVO> fetchCommentsByBoardID(int boardid) throws Exception {
		List<CommentVO> commentlist = commentMapper.fetchCommentsByBoardID(boardid);
		return commentlist;
	}
	
	@Override
	public CommentVO fetchcommentByID(int commentid) throws Exception {
		CommentVO comment = commentMapper.fetchcommentByID(commentid);
		return comment;
	}
	
	@Override
	public int updateComment(CommentVO comment) {
		 
		return commentMapper.updateComment(comment);
	}


	@Override
	public int insertComment(CommentVO comment) {
		// TODO Auto-generated method stub
		System.out.println("댓글 등록 요청");
		return commentMapper.insertComment(comment);
	}

	@Override
	public int deleteComment(int commentid) {
		int result = commentMapper.deleteComment(commentid);
		return result;
	}



	
}
