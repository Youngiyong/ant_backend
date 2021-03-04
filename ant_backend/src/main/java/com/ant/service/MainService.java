package com.ant.service;

import java.util.List;

import com.ant.vo.ChangedateVO;
import com.ant.vo.TodayVO;

public interface MainService {

	// 메인화면 경제 지표 순위 
	public List<TodayVO> indicatorRank();
	
	// 메인화면 경제 지표 순위 순 각 테이블 최근 일자 데이터
	public List<ChangedateVO> mainIndicatorCall(String tableName);
	
}
