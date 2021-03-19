package com.ant.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.ant.mapper.springboot.BoardMapper;
import com.ant.mapper.indicators.IndicatorMapper;

import com.ant.mapper.springboot.UserMapper;
import com.ant.vo.BoardVO;
import com.ant.vo.CorrVO;
import com.ant.vo.ExechangeRateKorVO;
import com.ant.vo.ExechangeRateVO;
import com.ant.vo.IndiCommentVO;
import com.ant.vo.Indicator1VO;
import com.ant.vo.Indicator2VO;
import com.ant.vo.KakaoUserVO;
import com.ant.vo.PaymentVO;
import com.ant.vo.TestVO;

@Service("IndicatorService")
public class IndicatorServiceImpl implements IndicatorService {

	private int totalRecCount;	
	
	private int pageTotalCount;		
	
	//한 페이지당 예약내역 수
	private int countPerPage = 5;
	
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
	
	//지표-유형1(국제금,WTI)
	public List<Indicator1VO> indicators1(String tableName, int num) {
		System.out.println("지표-유형1(국제금,WTI)");
		HashMap map = new HashMap();
		map.put("tableName", tableName);	
		map.put("num", num);
		return IndicatorMapper.indicators1(map);
	}
	
	//지표-유형2(미10년,미2년,달러인덱스,비트코인)
	public List<Indicator2VO> indicators2(String tableName, int num) {
		System.out.println("지표-유형2(미10년,미2년,달러인덱스,비트코인)");
		HashMap map = new HashMap();
		map.put("tableName", tableName);	
		map.put("num", num);
		return IndicatorMapper.indicators2(map);
	}


	//상관관계 절대값으로 최상위값 표현
	public List<CorrVO> corrAbs(String indi, int num) {
		System.out.println("상관관계 절대값으로 최상위값 표현");
		HashMap map = new HashMap();
		map.put("indi", indi);	
		map.put("num", num);
		return IndicatorMapper.corrAbs(map);
	}

	//댓글입력-지표
	public void insertIndicator(IndiCommentVO indicomment) {
		System.out.println("댓글-입력");
		IndicatorMapper.insertIndicator(indicomment);
	}
	
	//댓글리스트-지표
	public List<IndiCommentVO> fetchCommentsByIndID(String symbol, int number) {
		System.out.println("댓글-리스트");
		int firstRow = (number-1)*countPerPage;
		HashMap map = new HashMap();
		map.put("symbolname", symbol);
		map.put("number", firstRow);	
		return IndicatorMapper.fetchCommentsByIndID(map);
	}

	
	//댓글-삭제
	public int deleteUserByUpdate(int comment_id) {
		System.out.println("댓글삭제-서비스임플"+ comment_id);
		return IndicatorMapper.deleteUserByUpdate(comment_id);
		
	}

	
}
