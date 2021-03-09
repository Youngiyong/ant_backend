package com.ant.mapper.springboot;

import java.util.List;

import com.ant.vo.NewsVO;

public interface NewsMapper {
	
	public List<NewsVO> selectByNewsId(String newsId);
	
}
