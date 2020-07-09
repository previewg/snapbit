package com.hgr.mini1.controller;


import com.hgr.mini1.domain.entity.UserEntity;
import com.hgr.mini1.dto.UserDto;
import com.hgr.mini1.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@AllArgsConstructor
public class UserController {
    private UserService userService;
    HttpSession session;

    // 유저정보 페이지 단순 get 호출
    @GetMapping("/userInfo")
    public String userInfo(){
        return "userInfo";
    }

    // 로그인 페이지 단순 get 호출
    @GetMapping("/signIn")
    public String signIn() {
        return "signIn";
    }

    // 로그인 페이지 ajax post 호출
    @PostMapping("/signIn")
    @ResponseBody
    public Map<String, String> signInPost(UserDto userDto) {
        Map<String, String> result;
        result = userService.signIn(userDto);
        return result;
    }

    // 회원가입 페이지 단순 post 호출
    @PostMapping("/signUp")
    @ResponseBody
    public String signUpPost(UserDto userDto) {
        userService.signUp(userDto);
        return "success";
    }

    // 이메일 중복확인 ajax post 호출
    @PostMapping("/emailVerify")
    @ResponseBody
    public String emailVerify(UserDto userDto) {
        String verified;
        verified = userService.emailVerify(userDto);
        return verified;
    }

    // 닉네임 중복확인 ajax post 호출
    @PostMapping("/nicknameVerify")
    @ResponseBody
    public String nicknameVerify(UserDto userDto) {
        String verified;
        verified = userService.nicknameVerify(userDto);
        return verified;
    }

    // 로그아웃 ajax get 호출
    @GetMapping("/signOut")
    @ResponseBody
    public String signOut() {
        userService.signOut();
        return "success";
    }

    @PostMapping("/user/delete")
    @ResponseBody
    public String userDelete() {
        UserEntity userInfo = (UserEntity) session.getAttribute("user_info");
        userService.userDelete(userInfo);

        return "success";
    }



}
