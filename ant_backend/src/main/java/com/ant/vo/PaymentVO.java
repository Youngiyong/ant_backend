package com.ant.vo;
import java.util.Date;

import lombok.Data;
 
@Data
public class PaymentVO {
    int payment_id;
    String userId;
    Date payment_date;
    int payment_price;
    String cancle_reason;
    int refund_price;
    String manager_id;
    String manager_memo;
    
}