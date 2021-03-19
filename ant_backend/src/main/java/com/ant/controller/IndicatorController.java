package com.ant.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ant.mapper.springboot.BoardMapper;
import com.ant.mapper.indicators.IndicatorMapper;
import com.ant.mapper.springboot.PaymentMapper;
import com.ant.mapper.springboot.UserMapper;
import com.ant.service.BoardServiceImpl;
import com.ant.service.IndicatorServiceImpl;
import com.ant.service.PaymentServiceImpl;
import com.ant.service.UserServiceImpl;
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
import com.ant.vo.UserVO;
 
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/indicator")
public class IndicatorController {
 
    @Autowired
    IndicatorMapper indicatorMapper;
    
    @Autowired
    IndicatorServiceImpl indicatorService;
  
    //국외 환율 정보 리스트
    @GetMapping("/exeForeign")
	public List<ExechangeRateVO> exeForeignList(){
		System.out.println("국외 환율 정보 리스트");
		return indicatorService.exeForeignList();
	}
    
    //국내 환율 정보 리스트
    @GetMapping("/exeKorList")
	public List<ExechangeRateKorVO> exeKorList(){
		System.out.println("국내 환율 정보 리스트");
		return indicatorService.exeKorList();
	}
    
    
   //달러_유로_1주일_전체 수치
    @GetMapping("/labelDalAllList")
    public List<ExechangeRateVO> labelDalAllList(){
		System.out.println("달러_유로_1주일_전체 수치");
		return indicatorService.labelDalAllList();
	}
    
  //달러_유로_1일_전체 수치
    @GetMapping("/labelDalOneList")
    public List<ExechangeRateVO> labelDalOneList(){
		System.out.println("달러_유로_1일_전체 수치");
		return indicatorService.labelDalOneList();
	}
    
    //차트-국내환율
    @GetMapping("/chart/{num}")
	public List<ExechangeRateKorVO> chartIndi(@PathVariable int num){
		System.out.println("차트-국내환율");
		return indicatorService.chartIndi(num);
	}
    
    //차트-해외환율
    @GetMapping("/chart/{symbol}/{num}")
	public List<ExechangeRateVO> chartIndiFor(@PathVariable String symbol, @PathVariable int num){
		System.out.println("차트-해외환율");
		return indicatorService.chartIndiFor(symbol, num);
	}
    
    //지표-유형1(국제금,WTI)
    @GetMapping("/indi1/{tableName}/{num}")
	public List<Indicator1VO> indicators1(@PathVariable String tableName, @PathVariable int num){
		System.out.println("지표-유형1(국제금,WTI)");
		return indicatorService.indicators1(tableName, num);
	}
    
    //지표-유형2(미10년,미2년,달러인덱스,비트코인)
    @GetMapping("/indi2/{tableName}/{num}")
	public List<Indicator2VO> indicators2(@PathVariable String tableName, @PathVariable int num){
		System.out.println("지표-유형1(국제금,WTI)");
		return indicatorService.indicators2(tableName, num);
	}
    
    //상관관계 절대값으로 최상위값 표현
    @GetMapping("/corrAbs/{indi}/{num}")
	public List<CorrVO> corrAbs(@PathVariable String indi, @PathVariable int num){
		System.out.println("지표-유형1(국제금,WTI)");
		return indicatorService.corrAbs(indi, num);
	}
    
    //댓글-INSERT
    @PostMapping("/input")
	public void insertIndicator(@RequestBody IndiCommentVO indicomment) {
    	System.out.println("댓글-지표");
    	indicatorService.insertIndicator(indicomment);
	}
    
    //댓글-LisT
    @GetMapping("/comList2/{symbolname}/{num}")
	public List<IndiCommentVO> fetchCommentsByIndID(@PathVariable String symbolname, @PathVariable int num) {
    	int number = num-1;
    	System.out.println("댓글-리스트");
    	return indicatorService.fetchCommentsByIndID(symbolname, number);
	}
    
    //댓글-삭제
    @DeleteMapping("/del/{comment_id}")
	public int deleteUserByUpdate(@PathVariable int comment_id) {
    	System.out.println("댓글삭제");
    	return indicatorService.deleteUserByUpdate(comment_id);
	}
    
    
}