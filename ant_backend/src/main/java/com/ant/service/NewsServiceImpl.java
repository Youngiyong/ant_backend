package com.ant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ant.mapper.springboot.NewsMapper;
import com.ant.vo.NewsVO;

@Service("NewsService")
public class NewsServiceImpl {
	
	@Autowired
	NewsMapper NewsMapper;
	
	public List<NewsVO> selectByNewsId(String newsId) {
		return NewsMapper.selectByNewsId(newsId);
	}
	
}
