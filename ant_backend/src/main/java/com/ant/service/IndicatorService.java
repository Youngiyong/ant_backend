package com.ant.service;

import java.util.List;

import com.ant.vo.BoardVO;
import com.ant.vo.ExechangeRateKorVO;
import com.ant.vo.ExechangeRateVO;
import com.ant.vo.KakaoUserVO;
import com.ant.vo.PaymentVO;


public interface IndicatorService {
	// 국외 환율 List 
	public List<ExechangeRateVO> exeForeignList();
	
	// 국내 환율 List 
	public List<ExechangeRateKorVO> exeKorList();
	
	//달러_유로_1주일_전체 수치
	public List<ExechangeRateVO> labelDalAllList();
	
	//달러_유로_1일_전체_수치
	public List<ExechangeRateVO> labelDalOneList();
}
