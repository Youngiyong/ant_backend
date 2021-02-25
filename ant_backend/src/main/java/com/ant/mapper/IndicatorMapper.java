package com.ant.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.ant.vo.BoardVO;
import com.ant.vo.ExechangeRateKorVO;
import com.ant.vo.ExechangeRateVO;
import com.ant.vo.KakaoUserVO;
import com.ant.vo.PaymentVO;
import com.ant.vo.UserVO;
 
@Mapper
public interface IndicatorMapper {

	//국외 환율 정보 리스트
	List<ExechangeRateVO> exeForeignList();
	
	//국내 환율 정보 리스트
	List<ExechangeRateKorVO> exeKorList();

	//달러_유로_전체수치_리스트
	List<ExechangeRateVO> labelDalAllList();

	//달러_유로_1일_리스트
	List<ExechangeRateVO> labelDalOneList();
 
}