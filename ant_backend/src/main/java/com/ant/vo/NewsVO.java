package com.ant.vo;
import java.util.Date;

import lombok.Data;
 
@Data
public class NewsVO {
	int news_id;
	String news_title;
	String news_date;
	String news_content;
	String news_url;
	String news_source;
	String news_group;
	
	String keyword; // 키워드 받아오기
}
