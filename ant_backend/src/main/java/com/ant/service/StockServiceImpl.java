package com.ant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ant.mapper.ko.StockMapper;
import com.ant.vo.StockListVO;

@Service("StockService")
public class StockServiceImpl {
	
	@Autowired
	StockMapper stockMapper;
	
	// 개별 종목 정보 가져오기
	public StockListVO selectByStockId(String stockId) {
		return stockMapper.selectByStockId(stockId);
	}
	
}
