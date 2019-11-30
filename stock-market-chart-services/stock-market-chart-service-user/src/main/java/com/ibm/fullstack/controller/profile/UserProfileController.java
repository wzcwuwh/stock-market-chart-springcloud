package com.ibm.fullstack.controller.profile;

import com.alibaba.fastjson.JSONObject;
import com.ibm.fullstack.entity.User;
import com.ibm.fullstack.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "http://localhost:4200")
@RestController
public class UserProfileController {

    @Autowired
    private IUserService userService;

    @PutMapping(value = "/profile")
    public JSONObject userProfile(@RequestBody JSONObject userJson){
        String username = userJson.getString("username");
        String contactNo = userJson.getString("contactNo");
        User user = new User();
        user.setUsername(username);
        user.setContactNo(contactNo);
        user = userService.userProfile(user);
        JSONObject retJson = new JSONObject();
        if(user == null){
            retJson.put("data", null);
        } else{
            retJson.put("contactNo", user.getContactNo());
        }
        return retJson;
    }
}
