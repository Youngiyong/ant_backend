package com.ant.vo;
import java.util.Date;

import lombok.Data;
 
@Data
public class IndiCommentVO {
	int comment_id;
    String indiName;
    String nickName;
    int userId;
    String comment_content;
    int comment_LikeNum;
    int comment_hidden;
    Date comment_createData;
   
}