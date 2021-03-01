package com.ant.vo;
import lombok.Data;
 
@Data
public class CommentVO {
    int comment_id;
    int board_id;    
    String userid;
    String comment_createdata;
    int comment_LikeNum;
    String comment_content;
    int comment_hidden;
    String MANAGER_ID;

    String nickname;
}