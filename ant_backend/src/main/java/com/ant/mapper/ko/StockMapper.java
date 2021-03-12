package com.ant.mapper.ko;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ant.vo.StockListVO;

@Mapper
public interface StockMapper {
	
	// 개별 종목 정보 가져오기
	public StockListVO selectByStockId(String stockId);
	
}
