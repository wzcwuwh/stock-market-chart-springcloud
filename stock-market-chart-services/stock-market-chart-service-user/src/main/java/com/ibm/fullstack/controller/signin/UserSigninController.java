package com.ibm.fullstack.controller.signin;

import com.alibaba.fastjson.JSONObject;
import com.ibm.fullstack.entity.User;
import com.ibm.fullstack.service.IUserService;
import com.ibm.fullstack.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "http://localhost:4200")
@RestController
public class UserSigninController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/signin")
    public JSONObject userSignin(@RequestBody JSONObject userJson){
        String username = userJson.getString("username");
        String password = userJson.getString("password");
        User user = userService.userSignin(username, password);
        JSONObject retJson = new JSONObject();
        if(user == null){
            retJson.put("data", null);
        } else{
            Boolean resetPwd = user.getResetPwd();
            String userType = user.getUserType();
            retJson.put("resetPwd", resetPwd);
            retJson.put("userType", userType);
        }
        return retJson;
    }

    @PutMapping(value = "/pwd")
    public JSONObject userResetPwd(@RequestBody JSONObject userJson){
        String username = userJson.getString("username");
        String password = userJson.getString("password");
        User user = userService.userResetPwd(username, password);
        JSONObject retJson = new JSONObject();
        if(user == null){
            retJson.put("data", null);
        } else{
            Boolean resetPwd = user.getResetPwd();
            retJson.put("resetPwd", resetPwd);
        }
        return retJson;
    }
}
