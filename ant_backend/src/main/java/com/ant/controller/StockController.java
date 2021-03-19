package com.ant.controller;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.ant.service.StockServiceImpl;
import com.ant.vo.StockListVO;
import com.ant.vo.StockVO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/stock")
public class StockController {

	@Autowired
	StockServiceImpl stockService;

	// 개별 종목 정보 가져오기
	@GetMapping("/{stockId}")
	public StockListVO selectByStockId(@PathVariable String stockId) {
		System.out.println(stockId);
		return stockService.selectByStockId(stockId);
	}
	
	@GetMapping("/StocksList")
	public List<StockVO> selectByAllStocks(){
		List<StockVO> vo = stockService.selectByAllStocks();
		System.out.println(vo.get(0).getCode());
		System.out.println(vo.get(0).getDatetime());
		return vo;
	}
}
