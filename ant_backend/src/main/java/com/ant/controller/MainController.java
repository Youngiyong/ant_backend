package com.ant.controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
import com.ant.vo.IndicatorTableVO;
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
	public ArrayList<List<ChangedateVO>> indicatorRank(){
		
    	List<TodayVO> list;
    	list = MainService.indicatorRank();
    	Date date = list.get(0).getDates();
    	String[] temp = list.get(0).toString().replace(")","").split(", ");
    	
    	List<String> tableName = new ArrayList<String>();
    	List<Float> value = new ArrayList<Float>();
    	
    	for (int i=1; i<temp.length; i++) {
    		String[] tmp = temp[i].split("=");
    		tableName.add(tmp[0]);
    		value.add(Float.parseFloat(tmp[1]));
    	}
    	
    	ArrayList<IndicatorTableVO> tableList = new ArrayList<IndicatorTableVO>();
    	
    	for (int i=0; i<tableName.size(); i++) {
    		IndicatorTableVO tableObj = new IndicatorTableVO(tableName.get(i),value.get(i));
    		tableList.add(tableObj);
    	}
    	
    	Collections.sort(tableList, new Comparator<IndicatorTableVO>() {
    		
    		@Override
    		public int compare(IndicatorTableVO t1, IndicatorTableVO t2) {
    			if(t1.getChangedate() < t2.getChangedate()) {
    				return 1;
    			}
    			else if(t1.getChangedate() > t2.getChangedate()) {
    				return -1;
    			}
    			else {
    				return 0;
    			}
    		}
		});
    	
    	ArrayList<List<ChangedateVO>> result = new ArrayList<List<ChangedateVO>>();
    	
    	// 상위 4개 데이터 추출
    	for (int i=0; i<tableList.size()-4; i++) {
    		List<ChangedateVO> tempo = MainService.mainIndicatorCall(tableList.get(i).getTableName());
    		tempo.get(0).setTableName(tableList.get(i).getTableName());
    		System.out.println(tempo);
    		result.add(tempo);
    	}
    	return result;
	}
    
    // 메인화면 경제 지표 순위 순 각 테이블 최근 일자 데이터
    @GetMapping("/{tableName}")
	public List<ChangedateVO> mainIndicatorCall(@PathVariable String tableName){
		return MainService.mainIndicatorCall(tableName);
	}
	
}
