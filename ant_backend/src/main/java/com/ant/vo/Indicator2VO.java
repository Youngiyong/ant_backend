package com.ant.vo;
import java.util.Date;

import lombok.Data;
 
@Data
public class Indicator2VO {
	Date dates;
    float price;
    float open;
    float high;
    float low;
    String keyword;
    
}