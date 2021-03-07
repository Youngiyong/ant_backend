package com.ant.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ant.mapper.springboot.BoardMapper;
import com.ant.mapper.springboot.UserMapper;
import com.ant.service.BoardServiceImpl;
import com.ant.service.UserServiceImpl;
import com.ant.vo.BoardVO;
import com.ant.vo.KakaoUserVO;
import com.ant.vo.UserVO;
 
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/board")
public class BoardController {
 
    @Autowired
    BoardMapper boardMapper;
    
    @Autowired
    BoardServiceImpl boardService;
 

    @GetMapping
    public List<BoardVO> BoardListGet() throws Exception {
    	System.out.println("전체 최신순 게시물 요청");
    	List<BoardVO> boardList = boardService.boardListGet();
    	System.out.println(boardList);
        	return boardList;
    }
    @DeleteMapping("/{boardid}")
    public void deleteBoard(@PathVariable int boardid) {
    	boardService.deleteBoard(boardid);
        System.out.println("게시물 삭제 성공");
    }
    @GetMapping("/likedorder")
    public List<BoardVO> BoardListGetLiked() throws Exception {
    	System.out.println("전체 추천순 게시물 요청");
    	List<BoardVO> boardList = boardService.boardListGetLiked();
        	return boardList;
    }
    @GetMapping("/save")
    public List<BoardVO> BoardListGetSave() throws Exception {
    	System.out.println("좋아요? 글 게시물 요청");
    	List<BoardVO> boardList = boardService.boardListGetLiked();
        	return boardList;
    }
    @GetMapping("/savedboardCheck/{userid}")
    public List<String> fetchUserSavedBoardListCheck(@PathVariable int userid) throws Exception {
    	System.out.println("저장한 게시물 리스트 체크 요청");
    	List<String> boardList = boardService.fetchUserSavedBoardListCheck(userid);
        	return boardList;
    }
    @GetMapping("/savedboard/{userid}")
    public List<BoardVO> fetchUserSavedBoardList(@PathVariable int userid) throws Exception {
    	System.out.println("저장한 게시물 요청");
    	List<BoardVO> boardList = boardService.fetchUserSavedBoardList(userid);
        	return boardList;
    }
    @GetMapping("/savedboardLiked/{userid}")
    public List<BoardVO> fetchSavedUserBoardLiked(@PathVariable int userid) throws Exception {
    	System.out.println("저장한 게시물 요청");
    	List<BoardVO> boardList = boardService.fetchSavedUserBoardLiked(userid);
        	return boardList;
    }
    @PostMapping("/saved")
    void addSaveddUserBoard(@RequestBody BoardVO board) {
    	boardService.addSaveddUserBoard(board);
    	System.out.println("유저 저장한글 테이블에 게시판 저장");
        System.out.println(board);
    }
    @DeleteMapping("/saved/{board_id}/{userid}")
    void deleteSaveddUserBoard(@PathVariable int board_id,@PathVariable int userid) {
    	BoardVO deletedboard = new BoardVO();
    	deletedboard.setBoard_id(board_id);
    	deletedboard.setUserid(String.valueOf(userid));  	
    	boardService.deleteSaveddUserBoard(deletedboard);
    	System.out.println("유저 저장한글 테이블에 게시판 삭제");
    }
    
    
    @GetMapping("/{boardid}")
    public BoardVO fetchBoardByID(@PathVariable int boardid) throws Exception  {
        System.out.println("조회수카운트증가안함");
        BoardVO board = boardService.fetchBoardByID(boardid);
        return board;
    }
    @GetMapping("/{boardid}/count")
    public BoardVO fetchBoardByIDcount(@PathVariable int boardid) throws Exception{
        System.out.println("조회수카운트증가");
        boardService.BoardViewCount(boardid);
        BoardVO board = boardService.fetchBoardByID(boardid);
        return board;
    }

    @PutMapping("/{boardid}")
    public void updateBoard(@PathVariable int boardid, @RequestBody BoardVO board) {
        System.out.println("게시판 수정요청 ");
    	System.out.println(boardid);
    	System.out.println(board);
    	int result = boardService.updateBoard(board);
//        UserVO updateUser = user;
//        System.out.println("유저 업데이트 => " + updateUser);
//        
//        updateUser.setFirstName(user.getFirstName());
//        updateUser.setLastName(user.getLastName());
//        updateUser.setAge(user.getAge());
//        updateUser.setSalary(user.getSalary());
//        
//        userMapper.updateUser(updateUser); 
    }
    @PostMapping
    void insertBoard(@RequestBody BoardVO board) {
    	boardService.insertBoard(board);
    	System.out.println("게시판 추가 요청");
        System.out.println(board);
    }
    @PostMapping("/like")
    void insertUserlikeBoard(@RequestBody BoardVO board) {
    	boardService.insertUserLikedBoard(board);
    	System.out.println("좋아요 테이블 요청");
        System.out.println(board);
    }

    @DeleteMapping("/like/{board_id}/{userid}")
    public void deleteUserlikeBoard(@PathVariable int board_id,@PathVariable int userid) {
//        userMapper.deleteUser(id);
    	boardService.deleteUserlikeBoard(board_id,userid);
//    	System.out.println(board_id);
//    	System.out.println(userid);
    	
        System.out.println("좋아요 테이블 삭제 요청");
    }
    @GetMapping("/{board_id}/{userid}/likecheck")
    public String fetchUserlikeBoard(@PathVariable int board_id,@PathVariable int userid) throws Exception  {
        System.out.println("좋아요 테이블 체크 요청");
        int result = boardService.fetchUserlikeBoard(board_id,userid);
        return String.valueOf(result);
    }
    
}