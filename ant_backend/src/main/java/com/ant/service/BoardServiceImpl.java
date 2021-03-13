package com.ant.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ant.mapper.springboot.BoardMapper;
import com.ant.mapper.springboot.UserMapper;
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
	@Override
	public List<BoardVO> fetchTopMainBoards() throws Exception {
		List<BoardVO> boardlist = boardMapper.fetchTopMainBoards();
		return boardlist;
	}
	@Override
	public List<BoardVO> boardListGetLiked() throws Exception {
		List<BoardVO> boardlist = boardMapper.boardListLiked();
		return boardlist;
	}
	@Override
	public List<BoardVO> boardListGetSave() throws Exception {
		List<BoardVO> boardlist = boardMapper.boardListGetSave();
		return boardlist;
	}

	@Override
	public void BoardViewCount(int id) {
		boardMapper.BoardViewCount(id);
		
	}
	@Override
	public BoardVO fetchBoardByID(int id) {
		BoardVO board = boardMapper.fetchBoardByID(id);
		return board;
	}

	@Override
	public int updateBoard(BoardVO board) {
		 
		return boardMapper.updateBoard(board);
	}

	@Override
	public int insertUserLikedBoard(BoardVO board) {
		System.out.println("insertBoard Service");
			System.out.println("유저 좋아요 게시판 요청 ");	
			boardMapper.addUserLikeBoard(board);
		
		return 0;
	}

	@Override
	public int insertBoard(BoardVO board) {
		// TODO Auto-generated method stub
		System.out.println("유저 게시판 추가 요청 / 서비스 ");
		return boardMapper.insertBoard(board);
	}

	@Override
	public int deleteUserlikeBoard(int board_id, int userid) {
		Map map = new HashMap();
		map.put("board_id", board_id);
		map.put("userid", userid);
		boardMapper.deleteUserlikeBoard(map);
		return 0;
	}

	@Override
	public int fetchUserlikeBoard(int board_id, int userid) {
		Map map = new HashMap();
		map.put("board_id", board_id);
		map.put("userid", userid);
		int result = boardMapper.fetchUserlikeBoard(map);
		return result;
	}
	@Override
	public List<String> fetchUserSavedBoardListCheck(int userid) {
		List<String> boardlist = boardMapper.fetchUserSavedBoardListCheck(userid);
		return boardlist;
	}
	@Override
	public int addSaveddUserBoard(BoardVO board) {
		System.out.println("유저 저장한글 테이블에 게시판 저장 / 서비스");	
		boardMapper.addSaveddUserBoard(board);
	
		return 0;
	}
	@Override
	public int deleteSaveddUserBoard(BoardVO board) {
		System.out.println("유저 저장한글 테이블에 게시판 삭제");	
		boardMapper.deleteSaveddUserBoard(board);
	
		return 0;
	}
	
	@Override
	public List<BoardVO> fetchUserSavedBoardList(int userid) {
		List<BoardVO> boardlist = boardMapper.fetchUserSavedBoardList(userid);
		return boardlist;
	}
	@Override
	public List<BoardVO> fetchSavedUserBoardLiked(int userid) {
		List<BoardVO> boardlist = boardMapper.fetchSavedUserBoardLiked(userid);
		return boardlist;
	}
	@Override
	public int deleteBoard(int boardid) {
		int result = boardMapper.deleteBoard(boardid);
		return result;
	}






	
}
