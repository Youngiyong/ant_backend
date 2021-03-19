package com.ant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ant.mapper.ko.StockMapper;
import com.ant.vo.ChangedateVO;
import com.ant.vo.StockListVO;
import com.ant.vo.StockVO;

@Service("StockService")
public class StockServiceImpl {
	
	@Autowired
	StockMapper stockMapper;
	
	// 종목 테이블 목록
	public ArrayList<String> stockTables(){
		return stockMapper.stockTables();
	}
	
	// 각 테이블 최근 일자 데이터 가져오기
	public List<StockVO> latestData(ArrayList<String> tables){
		return stockMapper.latestData(tables);
	}
	
	// 개별 종목 정보 가져오기
	public StockListVO selectByStockId(String stockId) {
		return stockMapper.selectByStockId(stockId);
	}
	
	// 메인화면 경제 지표 순위 순 각 테이블 최근 일자 데이터
	public List<StockVO> mainIndicatorCall(String tableName) {
		return stockMapper.mainIndicatorCall(tableName);
	}

	// 메인화면 하락순
	public List<StockVO> decreaseStocks() {
		return stockMapper.decreaseStocks();
	}
	
	// 메인화면 상승순
	public List<StockVO> increaseStocks() {
		return stockMapper.increaseStocks();
	}
	
	// 전체 종목 리스트
	public List<StockVO> selectByAllStocks(){
		return stockMapper.selectByAllStocks();
	}
	
}
