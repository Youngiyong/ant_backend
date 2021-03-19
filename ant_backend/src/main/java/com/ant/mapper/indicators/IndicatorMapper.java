package com.ant.mapper.indicators;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.ant.vo.BoardVO;
import com.ant.vo.CorrVO;
import com.ant.vo.ExechangeRateKorVO;
import com.ant.vo.ExechangeRateVO;
import com.ant.vo.IndiCommentVO;
import com.ant.vo.Indicator1VO;
import com.ant.vo.Indicator2VO;
import com.ant.vo.KakaoUserVO;
import com.ant.vo.PaymentVO;
import com.ant.vo.UserVO;
 
@Mapper
public interface IndicatorMapper {

	//국외 환율 정보 리스트
	List<ExechangeRateVO> exeForeignList();
	
	//국내 환율 정보 리스트
	List<ExechangeRateKorVO> exeKorList();

	//달러_유로_전체수치_리스트
	List<ExechangeRateVO> labelDalAllList();

	//달러_유로_1일_리스트
	List<ExechangeRateVO> labelDalOneList();

	//차트-수치대입-국내환율
	List<ExechangeRateKorVO> chartIndi(int num);

	//차트-수치대입-해외환율
	List<ExechangeRateVO> chartIndiFor(HashMap map);
	
	//지표-유형1(국제금,WTI)
	List<Indicator1VO> indicators1(HashMap map);

	//지표-유형2(미10년,미2년,달러인덱스,비트코인)
	List<Indicator2VO> indicators2(HashMap map);
 
	//상관관계 절대값으로 최상위값 표현
	List<CorrVO> corrAbs(HashMap map);

	//댓글-원-달러
	void insertIndicator(IndiCommentVO indicomment);

	//댓글리스트-지표
	List<IndiCommentVO> fetchCommentsByIndID(HashMap map);

	//댓글-삭제
	 int deleteUserByUpdate(int comment_id);
}
