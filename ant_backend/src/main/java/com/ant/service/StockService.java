package com.ant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import com.ant.vo.StockListVO;
import com.ant.vo.StockVO;
import com.ant.vo.ChangedateVO;
public interface StockService {
	
	// 테이블 목록 
	public ArrayList<String> stockTables();
	
	// 개별 종목 정보 가져오기
	public StockListVO selectByStockId(String stockId);
	
	// 각 테이블 최근 일자 데이터 가져오기
	public List<StockVO> latestData(ArrayList<String> tables);
	
	// 메인화면 경제 지표 순위 순 각 테이블 최근 일자 데이터
	public List<StockVO> mainIndicatorCall(String tableName);
	
	// 메인화면 하락순
	public List<ChangedateVO> decreaseStocks();
	// 메인화면 상승순
	public List<ChangedateVO> increaseStocks();

	// 전체 종목 리스트
	public List<StockVO> selectByAllStocks();
}
