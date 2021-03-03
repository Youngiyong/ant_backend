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
import com.ant.mapper.springboot.PaymentMapper;
import com.ant.mapper.springboot.UserMapper;
import com.ant.service.BoardServiceImpl;
import com.ant.service.PaymentServiceImpl;
import com.ant.service.UserServiceImpl;
import com.ant.vo.BoardVO;
import com.ant.vo.KakaoUserVO;
import com.ant.vo.PaymentVO;
import com.ant.vo.UserVO;
 
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/payment")
public class PaymentController {
 
    @Autowired
    PaymentMapper paymentMapper;
    
    @Autowired
    PaymentServiceImpl paymentService;
    
    //결제 테이블에 데이터 insert
    @PostMapping
	public void insertPayment(@RequestBody PaymentVO payment) {
    	paymentService.insertPayment(payment);
		System.out.println("결제저장");
	}
 
    //고객 구독여부 modify
    @PutMapping("/{userId}")
	public void modifyPayment(@RequestBody PaymentVO payment){
		System.out.println("구독수정");
		paymentService.modifyPayment(payment);
	}
    
    //고객 구독여부 확인
    @GetMapping("/confirm/{userId}")
	public int conformSub(@PathVariable String userId){
		System.out.println("구독확인");
		return paymentService.conformSub(userId);
	}
}