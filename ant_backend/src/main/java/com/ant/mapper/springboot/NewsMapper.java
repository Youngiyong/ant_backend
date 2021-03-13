package com.ant.mapper.springboot;

import java.util.List;

import com.ant.vo.NewsVO;
import com.ant.vo.UserVO;

public interface NewsMapper {
	
	public List<NewsVO> selectByNewsId(String newsId);
	
	public List<NewsVO> selectKeywordByNewsId(String newsId);
	
	public UserVO selectKeywordByUserId(int userId);
	
	public void updateKeywordByUserId(UserVO vo);
}
