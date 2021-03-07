package com.ant.vo;
import lombok.Data;
 
@Data
public class KakaoUserVO {
    int userid;
    String email;
    String pass;
    String kakaoname;
    String nickname;
    String phone;
    String userdate;
    int subscripstat;
    int managestat;
    String commentlist;
}