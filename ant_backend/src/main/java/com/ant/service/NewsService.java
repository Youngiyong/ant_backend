package com.ant.service;

import java.util.List;

import com.ant.vo.NewsVO;
import com.ant.vo.UserVO;

public interface NewsService {
	
	public List<NewsVO> selectByNewsId(String newsId);
	
	public List<NewsVO> selectKeywordByNewsId(String newsId);
	
	public List<UserVO> selectKeywordByUserId(int userId);

	public void updateKeywordByUserId(UserVO vo);
}
