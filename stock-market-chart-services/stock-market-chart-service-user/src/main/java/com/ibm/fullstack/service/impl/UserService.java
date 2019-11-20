package com.ibm.fullstack.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ibm.fullstack.client.UserClient;
import com.ibm.fullstack.dao.UserDao;
import com.ibm.fullstack.entity.User;
import com.ibm.fullstack.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class UserService implements IUserService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private UserDao userDao;

    @Override
    public JSONObject sendThymeleafEmailVeriCode(String username) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        return userClient.sendThymeleafEmailVefiCode(jsonObject);
    }

    @Override
    public JSONObject sendThymeleafEmailTmpPwd(String username) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        JSONObject retJson = userClient.sendThymeleafEmailTmpPwd(jsonObject);
        String resetPwd = retJson.getString("resetPwd");
        User user = new User();
        user.setUsername(username);
        user.setPassword(resetPwd);
        user.setResetPwd(true);
        user.setResetPwdDate(new Date());
        userDao.save(user);
        return retJson;
    }

    @Override
    public User userSignin(String useranme, String password) {
        User user = userDao.findByUsernameAndPassword(useranme, password);
        return user == null ? null : user;
    }

    @Override
    public User userResetPwd(String username, String password) {
        User user = userDao.findByUsernameAndResetPwd(username, Boolean.TRUE);
        user.setPassword(password);
        user.setResetPwd(Boolean.FALSE);
        user.setResetPwdDate(new Date());
        userDao.save(user);
        return user;
    }
}
