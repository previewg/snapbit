package com.hgr.mini1.controller;


import com.hgr.mini1.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@Data
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpSession session;

    @GetMapping("/userInfo")
    public String userInfo(){
        return "userInfo";
    }



}
