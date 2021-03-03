package com.ant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ant.mapper.springboot.BoardMapper;
import com.ant.mapper.springboot.PaymentMapper;
import com.ant.mapper.springboot.UserMapper;
import com.ant.vo.BoardVO;
import com.ant.vo.KakaoUserVO;
import com.ant.vo.PaymentVO;


@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

	@Autowired
    PaymentMapper paymentMapper;

	//결제 테이블에 데이터 insert
	public void insertPayment(PaymentVO payment) {
		System.out.println("서비스 결제 저장");
		paymentMapper.insertPayment(payment);
		
	}

	//고객 구독여부 수정
	public void modifyPayment(PaymentVO payment) {
		System.out.println("고객 구독여부 수정");
		paymentMapper.modifyPayment(payment);
		
	}

	//고객 구독여부 확인
	public int conformSub(String userId) {
		System.out.println("고객 구독여부 확인");
		return paymentMapper.conformSub(userId);
	}

	
}
