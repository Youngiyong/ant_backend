package com.ant.vo;

import java.util.Date;

import lombok.Data;

@Data
public class StockVO {
	String name;
	String code;
	float change;
	int price;
	Date datetime;
	
}
