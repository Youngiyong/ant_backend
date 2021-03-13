package com.ant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ant.mapper.springboot.NewsMapper;
import com.ant.vo.NewsVO;
import com.ant.vo.UserVO;

@Service("NewsService")
public class NewsServiceImpl {
	
	@Autowired
	NewsMapper NewsMapper;
	
	public List<NewsVO> selectByNewsId(String newsId) {
		return NewsMapper.selectByNewsId(newsId);
	}
	
	public List<NewsVO> selectKeywordByNewsId(String newsId) {
		return NewsMapper.selectKeywordByNewsId(newsId);
	}
	
	public UserVO selectKeywordByUserId(int userId){
		return NewsMapper.selectKeywordByUserId(userId);
	}
	
	public void updateKeywordByUserId(UserVO vo) {
		NewsMapper.updateKeywordByUserId(vo);
	}
}
