package com.ant.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.ant.vo.ChangedateVO;
import com.ant.vo.NewsVO;

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
	
}
