package com.ant.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ant.mapper.springboot.BoardMapper;
import com.ant.mapper.springboot.CommentMapper;
import com.ant.mapper.springboot.DeclareMapper;
import com.ant.mapper.springboot.UserMapper;
import com.ant.vo.BoardVO;
import com.ant.vo.CommentVO;
import com.ant.vo.DeclaredVO;
import com.ant.vo.KakaoUserVO;


@Service("DeclareService")
public class DeclareServiceImpl implements DeclareService {

	@Autowired
	DeclareMapper declareMapper;

	@Override
	public int insertDeclare(DeclaredVO declare) {
		System.out.println(declare.getBoard_id());
		System.out.println(declare.getComment_id());
		declareMapper.insertDeclare(declare);
		return 0;
	}

	@Override
	public List<DeclaredVO> fetchDeclaredByID(String userid) {
		List<DeclaredVO> declaredlist = declareMapper.fetchDeclaredByID(userid);
		return declaredlist;
	}


	
}
