package com.ibm.fullstack.controller.logout;

import com.alibaba.fastjson.JSONObject;
import com.ibm.fullstack.entity.User;
import com.ibm.fullstack.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "http://localhost:4200")
@RestController
public class UserLogoutController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/logout")
    public JSONObject userLogout(@RequestBody JSONObject userJson){
        String username = userJson.getString("username");
        User user = userService.userLogout(username);
        JSONObject retJson = new JSONObject();
        if(user == null){
            retJson.put("data", null);
        } else{
            retJson.put("loginStatus", user.getLoginStatus());
        }
        return retJson;
    }
}
