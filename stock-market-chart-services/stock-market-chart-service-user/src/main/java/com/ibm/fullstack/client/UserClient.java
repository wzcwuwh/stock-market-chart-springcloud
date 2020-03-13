package com.ibm.fullstack.client;

import com.alibaba.fastjson.JSONObject;
import com.ibm.fullstack.hystrix.UserHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "eureka-client-service-mail", fallback = UserHystrix.class)
public interface UserClient {

    @PostMapping(value = "/mail/thymeleaf/veri/code")
    JSONObject sendThymeleafEmailVefiCode(@RequestBody JSONObject userJson);

    @PostMapping(value = "/mail/thymeleaf/reset/pwd")
    JSONObject sendThymeleafEmailTmpPwd(@RequestBody JSONObject userJson);
}
