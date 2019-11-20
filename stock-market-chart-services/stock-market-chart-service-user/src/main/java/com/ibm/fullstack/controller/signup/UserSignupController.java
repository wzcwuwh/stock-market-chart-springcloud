package com.ibm.fullstack.controller.signup;

import com.alibaba.fastjson.JSONObject;
import com.ibm.fullstack.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserSignupController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/veri/code")
    public JSONObject userSignup(@RequestBody JSONObject userJson){
        log.info("the username to signup is : {}", userJson.getString("username"));
        return userService.sendThymeleafEmailVeriCode(userJson.getString("username"));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/reset/pwd")
    public JSONObject userTmpPwd(@RequestBody JSONObject userJson){
        log.info("the username of reset pwd is : {}", userJson.getString("username"));
        return userService.sendThymeleafEmailTmpPwd(userJson.getString("username"));
    }
}
