package com.ant.mapper.springboot;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.ant.vo.BoardVO;
import com.ant.vo.KakaoUserVO;
import com.ant.vo.PaymentVO;
import com.ant.vo.UserVO;
 
@Mapper
public interface PaymentMapper {

	//결제 테이블에 데이터 insert
	void insertPayment(PaymentVO payment);
	
	//고객 구독여부 수정
	void modifyPayment(PaymentVO payment);

	//고객 구독여부 확인
	int conformSub(String userId);
 
 
    
}