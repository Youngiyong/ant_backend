package com.ant.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ant.mapper.indicators.MainMapper;
import com.ant.vo.ChangedateVO;

@Service("MainService")
public class MainServiceImpl {
	
	@Autowired
    MainMapper MainMapper;
	
	// 테이블 목록 
	public ArrayList<String> indicatorTables() {
		return MainMapper.indicatorTables();
	}
	
	// 각 테이블 최근 일자 데이터 가져오기
	public List<ChangedateVO> latestData(ArrayList<String> tables) {
		return MainMapper.latestData(tables);
	}
	
	// 메인화면 경제 지표 순위 순 각 테이블 최근 일자 데이터
	public List<ChangedateVO> mainIndicatorCall(String tableName) {
		return MainMapper.mainIndicatorCall(tableName);
	}
		
}
