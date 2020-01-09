package com.ibm.fullstack.service;

import com.alibaba.fastjson.JSONObject;
import com.ibm.fullstack.client.UserClient;
import com.ibm.fullstack.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        JSONObject userJson = userClient.findUserByUsername(jsonObject);
        User user = (User)(userJson.get("user"));
        if(user == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
