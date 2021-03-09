package com.ant.service;

import java.util.List;

import com.ant.vo.NewsVO;

public interface NewsService {
	
	public List<NewsVO> selectByNewsId(String newsId);
	
}
