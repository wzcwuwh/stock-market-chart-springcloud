package com.ibm.fullstack.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ibm.fullstack.entity.Company;
import com.ibm.fullstack.entity.StockPriceDetail;
import com.ibm.fullstack.service.impl.CompanyService;
import com.ibm.fullstack.service.impl.StockPriceDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@CrossOrigin(value = "http://localhost:4200")
@Slf4j
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private StockPriceDetailService stockPriceDetailService;

    public CompanyController(CompanyService companyService, StockPriceDetailService stockPriceDetailService) {
        this.companyService = companyService;
        this.stockPriceDetailService = stockPriceDetailService;
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
            BigDecimal currentPrice = stockPriceDetailService.findCurrentPriceByCompanyCode(company.getStockCode());
            JSONObject dataJson = new JSONObject();
            dataJson.put("logo", logo);
            dataJson.put("companyName", companyName);
            dataJson.put("CEO", CEO);
            dataJson.put("boardChairman", boardChairman);
            dataJson.put("turnOver", turnOver);
            dataJson.put("sector", sector);
            dataJson.put("briefWriteup", briefWriteup);
            dataJson.put("currentPrice", currentPrice);
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
        company.setLogo(companyJson.getString("logo"));
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
            String logo = company.getLogo();
            JSONObject dataJson = new JSONObject();
            dataJson.put("companyName", companyName);
            dataJson.put("CEO", CEO);
            dataJson.put("boardChairman", boardChairman);
            dataJson.put("turnOver", turnOver);
            dataJson.put("sector", sector);
            dataJson.put("briefWriteup", briefWriteup);
            dataJson.put("logo", logo);
            jsonArray.add(dataJson);
        }
        jsonObject.put("companies", jsonArray);
        return jsonObject;
    }

    @PostMapping(value = "/compare")
    public JSONObject compareCompany(@RequestBody JSONObject stockPriceDetailJson){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        String companyName = stockPriceDetailJson.getString("companyName");
        String stockExchangeName = stockPriceDetailJson.getString("stockExchangeName");

        Date fromPeriod = stockPriceDetailJson.getDate("fromPeriod");
        Date toPeriod = stockPriceDetailJson.getDate("toPeriod");
        String periodSize = stockPriceDetailJson.getString("periodSize");
        String periodUnit = stockPriceDetailJson.getString("periodUnit");

        List<Date> timeline = getTimeline(fromPeriod, toPeriod, periodSize, periodUnit);

        List<StockPriceDetail> stockPriceDetails = this.stockPriceDetailService.retrieveStockPriceDetails(companyName, stockExchangeName);
        stockPriceDetails.forEach((stockPriceDetail)->{
            log.info(stockPriceDetail.get_date().toString());
            log.info(stockPriceDetail.get_time().toString());
            log.info(stockPriceDetail.getCurrentPrice().toString());
            java.sql.Date currentDate= stockPriceDetail.get_date();
            java.sql.Time currentTime = stockPriceDetail.get_time();
            BigDecimal currentPrice = stockPriceDetail.getCurrentPrice();
            JSONObject dataJson = new JSONObject();
            dataJson.put("currentDate", currentDate.toString());
            dataJson.put("currentTime", currentTime.toString());
            dataJson.put("currentPrice", currentPrice);
            jsonArray.add(dataJson);
        });
        jsonObject.put("stockPriceDetails", jsonArray);
        return jsonObject;
    }

    @PostMapping(value = "/name")
    public JSONObject getCompanyNameByCompanyCode(@RequestBody JSONObject companyJson){
        JSONObject jsonObject = new JSONObject();
        String companyCode = companyJson.getString("companyCode");
        Company company = this.companyService.findCompanyNameByStockCode(companyCode);
        String companyName = company.getCompanyName();
        jsonObject.put("companyName", companyName);
        return jsonObject;
    }

    private List<Date> getTimeline(Date fromPeriod, Date toPeriod, String periodSize, String periodUnit){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromPeriod);

        List<Date> timeline = new ArrayList<>();
        while(calendar.getTime().before(toPeriod)){
            timeline.add(calendar.getTime());
            switch(periodUnit)
            {
                case "second" :
                    calendar.add(Calendar.SECOND, Integer.valueOf(periodSize));
                    break;
                case "minute" :
                    calendar.add(Calendar.MINUTE, Integer.valueOf(periodSize));
                    break;
                case "hour" :
                    calendar.add(Calendar.HOUR, Integer.valueOf(periodSize));
                    break;
                case "month" :
                    calendar.add(Calendar.MONTH, Integer.valueOf(periodSize));
                    break;
                case "year" :
                    calendar.add(Calendar.YEAR, Integer.valueOf(periodSize));
                    break;
            }
        }
        timeline.forEach((date -> {
            log.info(date.toString());
        }));
        return timeline;
    }

}
