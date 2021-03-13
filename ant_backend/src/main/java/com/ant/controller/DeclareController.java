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
import com.ant.mapper.springboot.CommentMapper;
import com.ant.mapper.springboot.UserMapper;
import com.ant.service.BoardServiceImpl;
import com.ant.service.CommentServiceImpl;
import com.ant.service.DeclareServiceImpl;
import com.ant.service.UserServiceImpl;
import com.ant.vo.BoardVO;
import com.ant.vo.CommentVO;
import com.ant.vo.DeclaredVO;
import com.ant.vo.KakaoUserVO;
import com.ant.vo.UserVO;
 
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/declare")
public class DeclareController {
 
    @Autowired
    CommentMapper commentMapper;
    
    @Autowired
    DeclareServiceImpl DeclareService;
 

    @PostMapping
    void addDeclare(@RequestBody DeclaredVO declare) {
    	System.out.println("신고 insert 요청");
    	DeclareService.insertDeclare(declare);

    }
    @GetMapping("/{userid}")
    public List<DeclaredVO> fetchDeclaredByID(@PathVariable String userid) throws Exception {
    	System.out.println("유저가 신고한 데이터 요청");
		List<DeclaredVO> declaredlist = DeclareService.fetchDeclaredByID(userid);
        return declaredlist;
    }
    
    
}