package com.ibm.fullstack.controller.security;

import com.alibaba.fastjson.JSONObject;
import com.ibm.fullstack.entity.User;
import com.ibm.fullstack.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserSecurityController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/security")
    public JSONObject userSecurity(@RequestBody JSONObject userJson){
        String username = userJson.getString("username");
        User user = userService.findByUsername(username);
        JSONObject retJson = new JSONObject();
        retJson.put("user", user);
        return retJson;
    }
}
