package com.ant.service;

import java.util.ArrayList;
import java.util.List;

import com.ant.vo.ChangedateVO;

public interface MainService {

	// 테이블 목록 
	public ArrayList<String> indicatorTables();
	
	// 각 테이블 최근 일자 데이터 가져오기
	public List<ChangedateVO> latestData(ArrayList<String> tables);
	
	// 메인화면 경제 지표 순위 순 각 테이블 최근 일자 데이터
	public List<ChangedateVO> mainIndicatorCall(String tableName);
	
}
