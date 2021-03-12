package com.ant.service;

import org.springframework.web.bind.annotation.PathVariable;

import com.ant.vo.StockListVO;

public interface StockService {
	
	// 개별 종목 정보 가져오기
	public StockListVO selectByStockId(String stockId);
	
}
