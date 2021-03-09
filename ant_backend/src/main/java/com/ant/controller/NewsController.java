package com.ant.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
	
	// 메인화면 경제 지표 순위 순 각 테이블 최근 일자 데이터
    @GetMapping("/{newsId}")
	public List<NewsVO> selectByNewsId(@PathVariable String newsId){
		return NewsService.selectByNewsId(newsId);
	}
	
}
