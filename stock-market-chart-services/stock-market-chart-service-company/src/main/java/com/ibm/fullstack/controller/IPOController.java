package com.ibm.fullstack.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ibm.fullstack.entity.IPOsPlanned;
import com.ibm.fullstack.service.impl.IPOService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@CrossOrigin(value = "http://localhost:4200")
@Slf4j
@RestController
public class IPOController {

    @Autowired
    private IPOService ipoService;

    public IPOController(IPOService ipoService) {
        this.ipoService = ipoService;
    }

    @PostMapping(value = "/IPO")
    public JSONObject createNewIPO(@RequestBody JSONObject IPOJson) {
        JSONObject jsonObject = new JSONObject();
        IPOsPlanned ipOsPlanned = new IPOsPlanned();
        ipOsPlanned.setCompanyName(IPOJson.getString("companyName"));
        ipOsPlanned.setStockExchange(IPOJson.getString("stockExchangeName"));

        String pricePerShare = IPOJson.getString("pricePerShare");
        BigDecimal pricePerShareNum = new BigDecimal(pricePerShare);
        ipOsPlanned.setPricePerShare(pricePerShareNum);

        String totalNoOfShares = IPOJson.getString("totalNoOfShares");
        Integer totalNoOfSharesInt = Integer.valueOf(totalNoOfShares);
        ipOsPlanned.setTotalNoOfShares(totalNoOfSharesInt);

        ipOsPlanned.setOpenDateTime(IPOJson.getDate("openDateTime"));
        ipOsPlanned.setRemarks(IPOJson.getString("remarks"));
        IPOsPlanned retIPO = ipoService.createNewIPO(ipOsPlanned);
        if(retIPO != null){
            jsonObject.put("data", "success");
        } else {
            jsonObject.put("data", "fail");
        }
        return jsonObject;
    }

    @GetMapping(value = "/IPOlist")
    public JSONObject getIPOList(){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        List<IPOsPlanned> ipoList = this.ipoService.getIPOList();
        for(IPOsPlanned ipo: ipoList){
            String companyName = ipo.getCompanyName();
            String stockExchange = ipo.getStockExchange();
            BigDecimal pricePerShare = ipo.getPricePerShare();
            Integer totalNoOfShares = ipo.getTotalNoOfShares();
            Date openDateTime = ipo.getOpenDateTime();
            String remarks = ipo.getRemarks();
            JSONObject dataJson = new JSONObject();
            dataJson.put("companyName", companyName);
            dataJson.put("stockExchange", stockExchange);
            dataJson.put("pricePerShare", pricePerShare);
            dataJson.put("totalNoOfShares", totalNoOfShares);
            dataJson.put("openDateTime", openDateTime);
            dataJson.put("remarks", remarks);
            jsonArray.add(dataJson);
        }
        jsonObject.put("ipoList", jsonArray);
        return jsonObject;
    }

}
