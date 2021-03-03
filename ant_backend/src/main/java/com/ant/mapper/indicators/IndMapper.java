package com.ant.mapper.indicators;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.ant.vo.TestVO;
 
@Mapper
public interface IndMapper {

	//국외 환율 정보 리스트
	List<TestVO> testInd();

}
