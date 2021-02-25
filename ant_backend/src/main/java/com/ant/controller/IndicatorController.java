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

import com.ant.mapper.BoardMapper;
import com.ant.mapper.IndicatorMapper;
import com.ant.mapper.PaymentMapper;
import com.ant.mapper.UserMapper;
import com.ant.service.BoardServiceImpl;
import com.ant.service.IndicatorServiceImpl;
import com.ant.service.PaymentServiceImpl;
import com.ant.service.UserServiceImpl;
import com.ant.vo.BoardVO;
import com.ant.vo.ExechangeRateKorVO;
import com.ant.vo.ExechangeRateVO;
import com.ant.vo.KakaoUserVO;
import com.ant.vo.PaymentVO;
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
    
}