package com.ibm.fullstack.service;

import com.alibaba.fastjson.JSONObject;
import com.ibm.fullstack.entity.User;

public interface IUserService {

    JSONObject sendThymeleafEmailVeriCode(String username);

    JSONObject sendThymeleafEmailTmpPwd(String username);

    User userSignin(String username, String password);

    User userResetPwd(String username, String password);

    JSONObject userSignup(String username, String password);

    User userLogout(String username);

    User userProfile(User user);

    User findByUsername(String username);
}
