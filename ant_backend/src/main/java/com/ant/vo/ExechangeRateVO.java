package com.ant.vo;
import java.util.Date;

import lombok.Data;
 
@Data
public class ExechangeRateVO {
	Date dates;
    String exechange_Name;
    String symbol;
    float price;
    
}