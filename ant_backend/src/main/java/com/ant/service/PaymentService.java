package com.ant.service;

import java.util.List;

import com.ant.vo.BoardVO;
import com.ant.vo.KakaoUserVO;
import com.ant.vo.PaymentVO;


public interface PaymentService {
	// 결제 테이블에 데이터 insert
	public void insertPayment(PaymentVO payment);
	
	//고객 구독여부 수정
	public void modifyPayment(PaymentVO payment);
	
	//고객 구독여부 확인
	public int conformSub(String userId);
}
