package com.ant.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ant.service.MainServiceImpl;
import com.ant.service.StockService;
import com.ant.service.StockServiceImpl;
import com.ant.vo.ChangedateVO;
import com.ant.vo.IndicatorTableVO;
import com.ant.vo.StockVO;

import org.json.JSONArray;
import org.json.JSONObject;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/main")
public class MainController {

	@Autowired
	MainServiceImpl MainService;

	@Autowired
	StockServiceImpl StockService;
	
	// 종목 크롤링 후 stocklist 테이블에 종목 등록
	@GetMapping("/stockRegistJSON")
	public ResponseEntity stockRegistJSON(){
		ArrayList<String> tables = StockService.stockTables();
		tables.remove("list");
		tables.remove("stocklist");
		List<StockVO> tablesDataset = StockService.latestData(tables);
		System.out.println(tablesDataset.size());
		
		JSONArray json = new JSONArray();
		for (int i=0; i<tablesDataset.size(); i++) {
			JSONObject obj = new JSONObject(tablesDataset.get(i));
			json.put(obj.toMap());
		}
		return new ResponseEntity<>(json.toString(), HttpStatus.OK);
	}

	@GetMapping("/stockRank/{sortStocks}")
	public List<StockVO> stockRank(@PathVariable String sortStocks){
		List<StockVO> result = null;
		if(sortStocks.equals("상승순")){
			 result = StockService.increaseStocks();
		} else if (sortStocks.equals("하락순")) {
			result = StockService.decreaseStocks();
		}
		
		return result;
	}
	
	
	// 메인화면 경제 지표 순위 근거
	@GetMapping("/indicatorRank/{sortNews}")
	public ArrayList<List<ChangedateVO>> indicatorRank(@PathVariable String sortNews) {

		// 테이블 목록 불러오기
		ArrayList<String> tables = MainService.indicatorTables();
		// 목록에서 corr, exechangerate, today 삭제
		tables.remove("corr");
		tables.remove("exechangerate");
		tables.remove("today");
		tables.remove("indicomment");

		List<ChangedateVO> tablesDataset = MainService.latestData(tables);

		for (int i = 0; i < tablesDataset.size(); i++) {
			tablesDataset.get(i).setTableName(tables.get(i));
		}

		// 상승한 순서로 정렬
		if (sortNews.equals("상승순")) {
			Collections.sort(tablesDataset, new Comparator<ChangedateVO>() {
				public int compare(ChangedateVO t1, ChangedateVO t2) {
					if (t1.getChangedate() < t2.getChangedate()) {
						return 1;
					} else if (t1.getChangedate() > t2.getChangedate()) {
						return -1;
					} else {
						return 0;
					}
				}
			});
		} else if (sortNews.equals("하락순")){
			// 하락한 순서로 정렬 (추가 예정 - State 파라미터 받아와 If 조건 필터)
			Collections.sort(tablesDataset, new Comparator<ChangedateVO>() {
				public int compare(ChangedateVO t1, ChangedateVO t2) {
					if (t1.getChangedate() > t2.getChangedate()) {
						return 1;
					} else if (t1.getChangedate() < t2.getChangedate()) {
						return -1;
					} else {
						return 0;
					}
				}
			});
		}

		ArrayList<List<ChangedateVO>> result = new ArrayList<List<ChangedateVO>>();

		// 상위 4개 데이터 불러오기
		for (int i = 0; i < 4; i++) {
			List<ChangedateVO> tempo = MainService.mainIndicatorCall(tablesDataset.get(i).getTableName());
			tempo.get(0).setTableName(tablesDataset.get(i).getTableName());
			System.out.println(tablesDataset.get(i).getTableName());
			result.add(tempo);
		}
		return result;
	}

	// 메인화면 경제 지표 순위 순 각 테이블 최근 일자 데이터
	@GetMapping("/{tableName}")
	public List<ChangedateVO> mainIndicatorCall(@PathVariable String tableName) {
		return MainService.mainIndicatorCall(tableName);
	}

}
