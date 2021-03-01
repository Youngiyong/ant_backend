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

import com.ant.mapper.UserMapper;
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

    
}