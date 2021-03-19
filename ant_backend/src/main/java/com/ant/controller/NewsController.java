package com.ant.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Collections;

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

import com.ant.mapper.springboot.NewsMapper;
import com.ant.service.NewsServiceImpl;
import com.ant.vo.BoardVO;
import com.ant.vo.ChangedateVO;
import com.ant.vo.NewsVO;
import com.ant.vo.UserVO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/news")
public class NewsController {
	
	@Autowired
	NewsMapper newsMapper;
    
    @Autowired
    NewsServiceImpl NewsService;
	
	// 뉴스 상세화면
    @GetMapping("/{newsId}")
	public List<NewsVO> selectByNewsId(@PathVariable String newsId){
		return NewsService.selectByNewsId(newsId);
	}
    
    // 뉴스 검색결과 키워드
    @GetMapping("/keywords/{searchResult}")
	public ArrayList<List<NewsVO>> selectKeywordByNewsId(@PathVariable String searchResult){

    	System.out.println("키워드");
    	System.out.println(searchResult);
    	
    	String[] temp = searchResult.split(",");
    	
    	ArrayList<List<NewsVO>> result = new ArrayList<List<NewsVO>>();
    	
    	for (int i=0; i<temp.length; i++) {
    		List<NewsVO> tempKeywords = NewsService.selectKeywordByNewsId(temp[i]);
        	result.add(tempKeywords);
    	}
    	
    	System.out.println(result);
    	
    	return result;
	}
    
    // 메인 화면 유저별 키워드 리스트
    @GetMapping("/selectkeywords/{userId}")
	public UserVO selectKeywordByUserId(@PathVariable int userId){
    	UserVO result = NewsService.selectKeywordByUserId(userId);
		System.out.println("여기"+result.getKeyword());
		System.out.println(result);

    	return result;
	}
    
    // 메인 화면 키워드 삭제
    @PutMapping("/deletekeywords/{userId}/{keyword}")
	public void deleteKeywordByUserId(@PathVariable int userId, @PathVariable String keyword){
    	UserVO result = NewsService.selectKeywordByUserId(userId);
    	String[] temp = result.getKeyword().split(",");
    	ArrayList<String> list = new ArrayList<>();
    	System.out.println(keyword);
    	for(int i=0; i<temp.length; i++) {
    		if(temp[i].equals(keyword)) {
    			System.out.println(keyword + " 같은 키워드");
    		}
    		else
    			list.add(temp[i]);
    		
    	}
    	String word = String.join(",", list).replaceAll(" ", "");

    	UserVO vo = new UserVO();
    	vo.setId(userId);
    	vo.setKeyword(word);
    	System.out.println(vo.getKeyword());
    	NewsService.updateKeywordByUserId(vo);
	}
    
    @PutMapping("/updatekeywords/{userId}/{keyword}")
   	public void updateKeywordByUserId(@PathVariable int userId, @PathVariable String keyword){
       	UserVO result = NewsService.selectKeywordByUserId(userId);
       	String[] temp = result.getKeyword().split(",");
       	ArrayList<String> list = new ArrayList<>();
       	list.add(keyword);
       	for(int i=0; i<temp.length; i++) {
       		if(temp[i].equals(keyword)) {
       			System.out.println(keyword + " 같은 키워드");
       		}
       		else
       			list.add(temp[i]);
       		
       	}
       	System.out.println("응"+list);
       	String word = String.join(",", list).replaceAll(" ", "");

       	UserVO vo = new UserVO();
       	vo.setId(userId);
       	vo.setKeyword(word);
       	System.out.println(vo.getKeyword());
       	NewsService.updateKeywordByUserId(vo);
   	}

}
