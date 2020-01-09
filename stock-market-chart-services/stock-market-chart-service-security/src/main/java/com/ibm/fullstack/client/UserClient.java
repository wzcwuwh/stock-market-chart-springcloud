package com.ibm.fullstack.client;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "eureka-client-service-user")
public interface UserClient {

    @PostMapping(value = "/user/security")
    JSONObject findUserByUsername(@RequestBody JSONObject userJson);
}
