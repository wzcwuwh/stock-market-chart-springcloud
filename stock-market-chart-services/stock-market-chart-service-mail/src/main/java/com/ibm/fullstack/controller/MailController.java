package com.ibm.fullstack.controller;

import com.alibaba.fastjson.JSONObject;
import com.ibm.fullstack.dto.MailBean;
import com.ibm.fullstack.service.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

@RestController
@Slf4j
public class MailController {

    @Autowired
    private IMailService mailService;

    public MailController(IMailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping(value = "/thymeleaf/veri/code")
    public JSONObject sendThymeleafMailVeriCode(@RequestBody JSONObject userJson){
        log.info(userJson.getString("username"));
        MailBean mailBean = new MailBean();
        mailBean.setSubject("[NEED YOU KNOW]Here is your verification code for your account");
        mailBean.setReceiver(userJson.getString("username"));
        mailBean.setContent("Here is your verification code for your account");
        Context context = new Context();
        Integer prefixCode = (int)(Math.random()*9000) + 1000;
        log.info("the prefixCode is : {}", prefixCode);
        context.setVariable("prefixCode", prefixCode);
        Integer suffixCode = (int)(Math.random()*900000) + 100000;
        log.info("the suffixCode is : {}", suffixCode);
        context.setVariable("suffixCode", suffixCode);
        mailService.sendTemplateMail(mailBean, "veri-code",context);

        JSONObject retJSON = new JSONObject();
        retJSON.put("prefixCode", prefixCode.toString());
        retJSON.put("suffixCode", suffixCode.toString());
        return retJSON;
    }

    @PostMapping(value = "/thymeleaf/reset/pwd")
    public JSONObject sendThymeleafMailTmpPwd(@RequestBody JSONObject userJson){
        log.info(userJson.getString("username"));
        MailBean mailBean = new MailBean();
        mailBean.setSubject("[NEED YOU KNOW]Here is your tmp pwd for your account");
        mailBean.setReceiver(userJson.getString("username"));
        mailBean.setContent("Here is your tmp pwd for your account");
        Context context = new Context();
        String resetPwd = RandomStringUtils.randomAlphanumeric(15).toLowerCase();
        log.info("random generated tmp pwd is : {}", resetPwd);
        context.setVariable("resetPwd", resetPwd);
        mailService.sendTemplateMail(mailBean, "tmp-pwd",context);

        JSONObject retJSON = new JSONObject();
        retJSON.put("resetPwd", resetPwd);
        return retJSON;
    }
}
