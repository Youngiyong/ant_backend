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

import com.ant.mapper.indicators.IndicatorMapper;
import com.ant.service.MainServiceImpl;
import com.ant.vo.ChangedateVO;
import com.ant.vo.TodayVO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/main")
public class MainController {
	
	@Autowired
    IndicatorMapper indicatorMapper;
    
    @Autowired
    MainServiceImpl MainService;
  
    // 메인화면 경제 지표 순위 근거 
    @GetMapping("/indicatorRank")
	public List<TodayVO> indicatorRank(){
		return MainService.indicatorRank();
	}
    
    // 메인화면 경제 지표 순위 순 각 테이블 최근 일자 데이터
    @GetMapping("/{tableName}")
	public List<ChangedateVO> mainIndicatorCall(@PathVariable String tableName){
		return MainService.mainIndicatorCall(tableName);
	}
	
}
