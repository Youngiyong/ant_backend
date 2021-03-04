package com.ant.mapper.indicators;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ant.vo.ChangedateVO;
import com.ant.vo.TodayVO;

@Mapper
public interface MainMapper {

	// 메인화면 경제 지표 순위 
	List<TodayVO> indicatorRank();
	
	// 메인화면 경제 지표 순위 순 각 테이블 최근 일자 데이터
	List<ChangedateVO> mainIndicatorCall(String tableName);
	
}
