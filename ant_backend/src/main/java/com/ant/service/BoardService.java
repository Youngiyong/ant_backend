package com.ant.service;

import java.util.List;

import com.ant.vo.BoardVO;
import com.ant.vo.KakaoUserVO;

public interface BoardService {

	public List<BoardVO> boardListGet() throws Exception;
//	public KakaoUserVO kakaofetchUserByID(String kakaoid) throws Exception;
	public void BoardViewCount(int id);
	public BoardVO fetchBoardByID(int id);

}
