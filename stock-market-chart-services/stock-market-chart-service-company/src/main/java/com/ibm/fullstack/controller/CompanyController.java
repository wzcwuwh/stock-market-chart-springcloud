package com.ibm.fullstack.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ibm.fullstack.entity.Company;
import com.ibm.fullstack.service.impl.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value = "http://localhost:4200")
@Slf4j
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(value = "/list")
    public JSONObject getCompanyList(){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        List<Company> companies = this.companyService.getCompanyList();
        for(Company company: companies){
            String logoPath = company.getLogoPath();
            String companyName = company.getCompanyName();
            String briefWriteup = company.getBriefWriteup();
            JSONObject dataJson = new JSONObject();
            dataJson.put("logoPath", logoPath);
            dataJson.put("companyName", companyName);
            dataJson.put("briefWriteup", briefWriteup);
            jsonArray.add(dataJson);
        }
        jsonObject.put("companies", jsonArray);
        return jsonObject;
    }

    @PostMapping(value = "/new")
    public JSONObject createNewCompany(@RequestBody JSONObject companyJson){
        JSONObject jsonObject = new JSONObject();
        Company company = new Company();
        company.setCompanyName(companyJson.getString("companyName"));
        company.setCEO(companyJson.getString("CEO"));
        company.setBoardOfDirectors(companyJson.getString("boardOfDirectors"));
        company.setTurnover(companyJson.getBigDecimal("turnover"));
        company.setBriefWriteup(companyJson.getString("briefWriteup"));
        Company retCompany = companyService.createNewCompany(company);
        if(retCompany != null){
            jsonObject.put("data", "success");
        } else {
            jsonObject.put("data", "fail");
        }
        return jsonObject;
    }
}
