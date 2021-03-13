package com.ant.service;

import java.util.List;

import com.ant.vo.BoardVO;
import com.ant.vo.KakaoUserVO;

public interface BoardService {

	public List<BoardVO> boardListGet() throws Exception;
	public List<BoardVO> fetchTopMainBoards() throws Exception;
	public List<BoardVO> boardListGetLiked() throws Exception;
	public List<BoardVO> boardListGetSave() throws Exception;
//	public KakaoUserVO kakaofetchUserByID(String kakaoid) throws Exception;
	public void BoardViewCount(int id);
	public BoardVO fetchBoardByID(int id);
	public int updateBoard(BoardVO board);
	public int insertBoard(BoardVO board);
	public int insertUserLikedBoard(BoardVO board);
	public int deleteUserlikeBoard(int board_id,int userid);
	public int fetchUserlikeBoard(int board_id,int userid);
	public List<String> fetchUserSavedBoardListCheck(int userid);
	public int addSaveddUserBoard(BoardVO board);
	public int deleteSaveddUserBoard(BoardVO board);
	public List<BoardVO> fetchUserSavedBoardList(int userid);
	public List<BoardVO> fetchSavedUserBoardLiked(int userid);
	public int deleteBoard(int boardid);
}
