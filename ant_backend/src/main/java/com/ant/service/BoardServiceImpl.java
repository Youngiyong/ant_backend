package com.ant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ant.mapper.BoardMapper;
import com.ant.mapper.UserMapper;
import com.ant.vo.BoardVO;
import com.ant.vo.KakaoUserVO;


@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
    BoardMapper boardMapper;

	@Override
	public List<BoardVO> boardListGet() throws Exception {
		List<BoardVO> boardlist = boardMapper.boardList();
		return boardlist;
	}


	
}
