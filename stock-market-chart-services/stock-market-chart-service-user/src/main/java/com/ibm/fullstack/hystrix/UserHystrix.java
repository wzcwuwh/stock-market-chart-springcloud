package com.ibm.fullstack.hystrix;

import com.alibaba.fastjson.JSONObject;
import com.ibm.fullstack.client.UserClient;
import org.springframework.stereotype.Component;

@Component
public class UserHystrix implements UserClient {

    @Override
    public JSONObject sendThymeleafEmailVefiCode(JSONObject userJson) {
        return null;
    }

    @Override
    public JSONObject sendThymeleafEmailTmpPwd(JSONObject userJson) {
        return null;
    }
}
