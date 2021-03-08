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
import com.ant.service.UserServiceImpl;
import com.ant.vo.BoardVO;
import com.ant.vo.CommentVO;
import com.ant.vo.KakaoUserVO;
import com.ant.vo.UserVO;
 
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/comment")
public class CommentController {
 
    @Autowired
    CommentMapper commentMapper;
    
    @Autowired
    CommentServiceImpl commentService;
 

    @GetMapping
    public List<CommentVO> CommentListGet() throws Exception {
    	System.out.println("전체 댓글 요청");
    	List<CommentVO> commentList = commentService.CommentListGet();
    	System.out.println(commentList);
        	return commentList;
    }
    @GetMapping("/boardcomments/{boardid}")
    public List<CommentVO> fetchCommentsByBoardID(@PathVariable int boardid) throws Exception  {
    	System.out.println("해당 게시물의 댓글들 요청");
    	List<CommentVO>  commentlist = commentService.fetchCommentsByBoardID(boardid);
        return commentlist;
    }

    @GetMapping("/{commentid}")
    public CommentVO fetchcommentByID(@PathVariable int commentid) throws Exception  {
    	CommentVO comment = commentService.fetchcommentByID(commentid);
        return comment;
    }
    @DeleteMapping("/{commentid}")
    public void deleteComment(@PathVariable int commentid) {
    	commentService.deleteComment(commentid);
        System.out.println("댓글 삭제 성공");
    }

    @PutMapping("/{commentid}")
    public void updateComment(@PathVariable int commentid, @RequestBody CommentVO comment) {
        System.out.println("댓글 수정요청 ");
    	System.out.println(commentid);
    	System.out.println(comment);
    	int result = commentService.updateComment(comment);

    }
    
    @PostMapping
    public void insertComment(@RequestBody CommentVO comment) throws Exception {
    	System.out.println("댓글 등록 요청");
    	commentService.insertComment(comment);
    }
}