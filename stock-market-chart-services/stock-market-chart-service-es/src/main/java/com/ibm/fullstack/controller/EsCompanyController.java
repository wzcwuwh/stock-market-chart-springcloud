package com.ibm.fullstack.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ibm.fullstack.domain.EsCompany;
import com.ibm.fullstack.service.EsCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EsCompanyController {

    @Autowired
    private EsCompanyService esCompanyService;

    @PostMapping(value = "/search")
    public JSONObject search(@RequestBody JSONObject companyJson){
        String companyName = companyJson.getString("companyName");
        String boardChairman = companyJson.getString("boardChairman");
        String briefWriteup = companyJson.getString("briefWriteup");
        List<EsCompany> esCompanyList = esCompanyService.search(companyName, boardChairman, briefWriteup);
        JSONArray jsonArray = new JSONArray();
        esCompanyList.stream().forEach((esCompany)->{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("companyId", esCompany.getCompanyId());
            jsonObject.put("companyName", esCompany.getCompanyName());
            jsonObject.put("boardChairman", esCompany.getBoardChairman());
            jsonObject.put("briefWriteup", esCompany.getBriefWriteup());
            jsonArray.add(jsonObject);
        });
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("companyList", jsonArray);
        return jsonObject;
    }
}
