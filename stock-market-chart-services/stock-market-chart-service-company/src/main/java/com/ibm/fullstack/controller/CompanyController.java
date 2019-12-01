package com.ibm.fullstack.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ibm.fullstack.entity.Company;
import com.ibm.fullstack.service.impl.CompanyService;
import com.ibm.fullstack.service.impl.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;

@CrossOrigin(value = "http://localhost:4200")
@Slf4j
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private FileStorageService fileStorageService;

    public CompanyController(CompanyService companyService, FileStorageService fileStorageService) {
        this.companyService = companyService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping(value = "/list")
    public JSONObject getCompanyList(){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        List<Company> companies = this.companyService.getCompanyList();
        for(Company company: companies){
            String logo = company.getLogo();
            String companyName = company.getCompanyName();
            String CEO = company.getCEO();
            String boardChairman = company.getBoardChairman();
            BigDecimal turnOver = company.getTurnover();
            String sector = company.getSector();
            String briefWriteup = company.getBriefWriteup();
            JSONObject dataJson = new JSONObject();
            dataJson.put("logo", logo);
            dataJson.put("companyName", companyName);
            dataJson.put("CEO", CEO);
            dataJson.put("boardChairman", boardChairman);
            dataJson.put("turnOver", turnOver);
            dataJson.put("sector", sector);
            dataJson.put("briefWriteup", briefWriteup);
            jsonArray.add(dataJson);
        }
        jsonObject.put("companies", jsonArray);
        return jsonObject;
    }

    @PostMapping(value = "/new")
    public JSONObject createNewCompany(@RequestBody JSONObject companyJson) throws IOException {
        JSONObject jsonObject = new JSONObject();
        Company company = new Company();
        company.setCompanyName(companyJson.getString("companyName"));
        company.setCEO(companyJson.getString("CEO"));
        company.setBoardChairman(companyJson.getString("boardChairman"));
        company.setTurnover(companyJson.getBigDecimal("turnover"));
        company.setSector(companyJson.getString("sector"));
        company.setBriefWriteup(companyJson.getString("briefWriteup"));

        String logo = companyJson.getString("logo");
        log.info(logo);
//        BASE64Decoder decoder = new BASE64Decoder();
//        byte[] bytes =new byte[0] ;
//        if(logoPath.contains("data:image/png;base64")){
//            //Base64解码
//            bytes=decoder.decodeBuffer(logoPath.replace("data:image/png;base64,", ""));
//        }
//        if(logoPath.contains("data:image/jpeg;base64")){
//            //Base64解码
//            bytes=decoder.decodeBuffer(logoPath.replace("data:image/jpeg;base64,", ""));
//        }
//        for(int i=0;i<bytes.length;++i) {
//            if(bytes[i]<0) {
//                //adjust exceptional data
//                bytes[i]+=256;
//            }
//        }
        company.setLogo(logo);

        Company retCompany = companyService.createNewCompany(company);
        if(retCompany != null){
            jsonObject.put("data", "success");
        } else {
            jsonObject.put("data", "fail");
        }
        return jsonObject;
    }

    @PostMapping(value = "/search")
    public JSONObject searchCompany(@RequestBody JSONObject companyJson){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        String companySearchTxt = companyJson.getString("companySearchTxt");
        List<Company> companies = companyService.searchCompany(companySearchTxt);
        for(Company company: companies){
            String companyName = company.getCompanyName();
            String CEO = company.getCEO();
            String boardChairman = company.getBoardChairman();
            BigDecimal turnOver = company.getTurnover();
            String sector = company.getSector();
            String briefWriteup = company.getBriefWriteup();
            JSONObject dataJson = new JSONObject();
            dataJson.put("companyName", companyName);
            dataJson.put("CEO", CEO);
            dataJson.put("boardChairman", boardChairman);
            dataJson.put("turnOver", turnOver);
            dataJson.put("sector", sector);
            dataJson.put("briefWriteup", briefWriteup);
            jsonArray.add(dataJson);
        }
        jsonObject.put("companies", jsonArray);
        return jsonObject;
    }

}
