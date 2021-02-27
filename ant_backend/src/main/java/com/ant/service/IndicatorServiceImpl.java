package com.ant.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ant.mapper.BoardMapper;
import com.ant.mapper.IndicatorMapper;
import com.ant.mapper.UserMapper;
import com.ant.vo.BoardVO;
import com.ant.vo.ExechangeRateKorVO;
import com.ant.vo.ExechangeRateVO;
import com.ant.vo.KakaoUserVO;
import com.ant.vo.PaymentVO;

@Service("IndicatorService")
public class IndicatorServiceImpl implements IndicatorService {

	@Autowired
    IndicatorMapper IndicatorMapper;

	//국외 환율 정보 리스트
	public List<ExechangeRateVO> exeForeignList() {
		System.out.println("국외 환율 정보 리스트-서비스");
		return IndicatorMapper.exeForeignList();
	}
	
	//국내 환율 정보 리스트
	public List<ExechangeRateKorVO> exeKorList() {
		System.out.println("국내 환율 정보 리스트-서비스");
		return IndicatorMapper.exeKorList();
	}


	 //달러_유로_1주일_전체 수치
	public List<ExechangeRateVO> labelDalAllList() {
		System.out.println("달러-유로-라벨 리스트-서비스-전체수치");
		return IndicatorMapper.labelDalAllList();
	}

	//달러_유로_1일_전체 수치
	public List<ExechangeRateVO> labelDalOneList() {
		System.out.println("달러-유로-라벨 1일-서비스-전체수치");
		return IndicatorMapper.labelDalOneList();
	}

	//차트-숫자대입-원/달러
	public List<ExechangeRateKorVO> chartIndi(int num) {
		System.out.println("차트-숫자대입-원/달러");		
		return IndicatorMapper.chartIndi(num);
	}

	//차트-해외환율
	public List<ExechangeRateVO> chartIndiFor(String symbol, int num) {
		System.out.println("차트-숫자대입-해외환율");
		HashMap map = new HashMap();
		map.put("symbol", symbol);	
		map.put("num", num);
		return IndicatorMapper.chartIndiFor(map);
	}
	

	
}
