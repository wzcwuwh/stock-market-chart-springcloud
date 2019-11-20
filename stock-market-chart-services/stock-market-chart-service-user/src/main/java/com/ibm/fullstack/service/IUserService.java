package com.ibm.fullstack.service;

import com.alibaba.fastjson.JSONObject;
import com.ibm.fullstack.entity.User;

public interface IUserService {

    JSONObject sendThymeleafEmailVeriCode(String username);

    JSONObject sendThymeleafEmailTmpPwd(String username);

    User userSignin(String useranme, String password);

    User userResetPwd(String username, String password);
}
