package com.ant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ant.mapper.indicators.MainMapper;
import com.ant.vo.ChangedateVO;
import com.ant.vo.TodayVO;

@Service("MainService")
public class MainServiceImpl {
	
	@Autowired
    MainMapper MainMapper;
	
	// 메인화면 경제 지표 순위 
	public List<TodayVO> indicatorRank() {
		return MainMapper.indicatorRank();
	}
		
	// 메인화면 경제 지표 순위 순 각 테이블 최근 일자 데이터
	public List<ChangedateVO> mainIndicatorCall(String tableName) {
		return MainMapper.mainIndicatorCall(tableName);
	}
		
}
