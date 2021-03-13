package com.ant.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ant.mapper.springboot.UserMapper;
import com.ant.service.UserServiceImpl;
import com.ant.vo.BoardVO;
import com.ant.vo.KakaoUserVO;
import com.ant.vo.UserVO;
 
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
 
    @Autowired
    UserMapper userMapper;
    
    @Autowired
    UserServiceImpl userService;
 
    @GetMapping
    public List<KakaoUserVO> UserListGet() throws Exception {
    	System.out.println("전체 유저 리스트 요청");
    	List<KakaoUserVO> userList = userService.UserListGet();
        	return userList;
    }
    @GetMapping("/totalcount")
    public int UserTotalCountGet() throws Exception {
    	System.out.println("전체 유저 수 요청");
    	int TotalCount = userService.UserTotalCountGet();
        	return TotalCount;
    }
    @GetMapping("/subcount")
    public int UserSubCountGet() throws Exception {
    	System.out.println("구독 유저 수 요청");
    	int SubCount = userService.UserSubCountGet();
        	return SubCount;
    }
    @GetMapping("/{id}")
    public Object fetchUserByID(@PathVariable int id) throws Exception {
        System.out.println(id);
        KakaoUserVO fetchUser = userService.kakaofetchUserByID(String.valueOf(id));
        // db에 id가 없는경우
        if(fetchUser==null) {
        	return "신규회원";
        }
        else {
        // db에 id가 있는경우
        System.out.println(fetchUser);
        	return fetchUser;
        }
    }
    @PostMapping
    public void insertUser(@RequestBody KakaoUserVO kakaouser) throws Exception {
//      userMapper.kakaoinsertUser(kakaouser);
    	
    	System.out.println("컨트롤러");
    	System.out.println(kakaouser.getEmail());
    	

    	userService.userJoin(kakaouser);
        System.out.println("카카오 유저 저장 성공");
    }
    @DeleteMapping("/{id}")
    public int deleteUser(@PathVariable int id) throws Exception {
    	int result = userService.deleteUser(id);
    	System.out.println(result);
        System.out.println("유저 삭제 성공");
        return result;
    }
    
    @PutMapping("/{id}")
    public void updateUser(@PathVariable String id, @RequestBody KakaoUserVO user) throws Exception {
        System.out.println("뭐지???");
    	KakaoUserVO updateUser = user;
        System.out.println("유저 업데이트 => " + updateUser);
        
         userService.updateUser(updateUser); 
    }
    
    @PutMapping("/UserLikedComment/{userid}/{comment_id}")
    public void editUserLikedComment(@PathVariable String userid, @PathVariable String comment_id) throws Exception {
        System.out.println(userid);
        System.out.println(comment_id);
        userService.editUserLikedComment(userid,comment_id);
//        System.out.println("유저 업데이트 => " + updateUser);
        
//         userService.updateUser(updateUser); 
    }
    
    @GetMapping("/UserLikedCommentList/{userid}")
    public List<String> getUserLikedCommentList(@PathVariable String userid) throws Exception {
    	System.out.println("유저 좋아요 댓글 리스트 가져오기");
        System.out.println(userid);
        
        List<String> commentlist = userService.getUserLikedCommentList(userid);
        
        return commentlist;
    }
    
    @GetMapping("/UserLikedBoardList/{userid}")
    public List<String> fetchUserLikedBoardList(@PathVariable String userid) throws Exception {
    	System.out.println("유저 좋아요 게시물 리스트 가져오기");
        System.out.println(userid);
        
        List<String> boardlist = userService.fetchUserLikedBoardList(userid);
        
        return boardlist;
    }
    //컨트롤러-마이페이지-회원정보보기
    @GetMapping("/profile/{id}")
	public KakaoUserVO profileShow(@PathVariable int id) {
		System.out.println("컨트롤러-마이페이지-회원정보보기" );
		KakaoUserVO fetchUser = userService.profileShow(id);
		return fetchUser;
	}
    
  //컨트롤러-마이페이지-회원정보수정
    @PutMapping("/profile/{id}")
	public void profileEdit(@PathVariable int id, @RequestBody KakaoUserVO user) {
    	KakaoUserVO updateUser = user;
		System.out.println("마이페이지 유저 업데이트 ==>" + updateUser);
		
		updateUser.setNickname(user.getNickname());
		updateUser.setPhone(user.getPhone());

		userService.profileEdit(updateUser);
	}
    
    //컨트롤러-마이페이지-비밀번호수정
    @PutMapping("/password/{id}")
	public void passwordEdit(@PathVariable int id, @RequestBody KakaoUserVO user) {
    	KakaoUserVO updateUser = user;
		System.out.println("마이페이지 비밀번호 업데이트 ==>" + updateUser);
		
		updateUser.setPass(user.getPass());
		
		userService.passwordEdit(updateUser);
	}
    
    //컨트롤러-마이페이지-나의 게시글
    @GetMapping("/board/{id}")
	public List<BoardVO> boardShow(@PathVariable int id) {
		System.out.println("컨트롤러-내가 쓴 게시글" );
		return userService.boardShow(id);
	}
}