package com.ant.service;

import java.util.List;

import com.ant.vo.BoardVO;
import com.ant.vo.ExechangeRateKorVO;
import com.ant.vo.ExechangeRateVO;
import com.ant.vo.Indicator1VO;
import com.ant.vo.Indicator2VO;
import com.ant.vo.KakaoUserVO;
import com.ant.vo.PaymentVO;
import com.ant.vo.TestVO;


public interface IndicatorService {
	
	// 테스트
	public List<TestVO> testInd();
	
	// 국외 환율 List 
	public List<ExechangeRateVO> exeForeignList();
	
	// 국내 환율 List 
	public List<ExechangeRateKorVO> exeKorList();
	
	//달러_유로_1주일_전체 수치
	public List<ExechangeRateVO> labelDalAllList();
	
	//달러_유로_1일_전체_수치
	public List<ExechangeRateVO> labelDalOneList();
	
	//차트-숫자대입-국내환율
	public List<ExechangeRateKorVO> chartIndi(int num);
	
	//차트-숫자대입-해외환율
	public List<ExechangeRateVO> chartIndiFor(String symbol, int num);
	
	//지표-유형1(국제금,WTI)
	public List<Indicator1VO> indicators1(String tableName, int num);
	
	//지표-유형2(미10년,미2년,달러인덱스,비트코인)
	public List<Indicator2VO> indicators2(String tableName, int num);
}
