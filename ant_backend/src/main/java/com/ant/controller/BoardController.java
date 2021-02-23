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
import org.springframework.web.bind.annotation.RestController;

import com.ant.mapper.BoardMapper;
import com.ant.mapper.UserMapper;
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
    	System.out.println("게시판 컨트롤러 요청");
    	List<BoardVO> boardList = boardService.boardListGet();

        	return boardList;
        
    }

    
}