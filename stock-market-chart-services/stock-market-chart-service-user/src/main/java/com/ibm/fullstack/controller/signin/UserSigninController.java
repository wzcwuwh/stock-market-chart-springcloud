package com.ibm.fullstack.controller.signin;

import com.alibaba.fastjson.JSONObject;
import com.ibm.fullstack.common.CommonResult;
import com.ibm.fullstack.entity.User;
import com.ibm.fullstack.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(value = "http://localhost:4200")
@RestController
public class UserSigninController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/signin")
    public JSONObject userSignin(@RequestBody JSONObject userJson){
        log.info("user is about to signin");
        String username = userJson.getString("username");
        String password = userJson.getString("password");
        User user = userService.userSignin(username, password);
        JSONObject retJson = new JSONObject();
//        CommonResult commonResult = null;
        if(user != null){
            Boolean resetPwd = user.getResetPwd();
            String userType = user.getUserType();

            retJson.put("resetPwd", resetPwd);
            retJson.put("userType", userType);
//            commonResult =  CommonResult.success(retJson);
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
