package com.ibm.fullstack.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ibm.fullstack.dto.ExcelStockData;
import com.ibm.fullstack.entity.StockPriceDetail;
import com.ibm.fullstack.service.IExcelService;
import com.ibm.fullstack.util.ExcelDataToMysqlData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@CrossOrigin(value = "http://localhost:4200")
@Slf4j
@RestController
public class ExcelController {

    @Autowired
    private IExcelService excelService;

    @PostMapping(value = "/import", consumes = "multipart/form-data")
    public JSONObject importExcel(@RequestParam(value = "file") MultipartFile file){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        List<ExcelStockData> excelStockData = excelService.importExcel(file, 0, 1, ExcelStockData.class);
        int count = 0;
        for(ExcelStockData esd: excelStockData){
            StockPriceDetail stockPriceDetail = ExcelDataToMysqlData.transform(esd);
            StockPriceDetail retStockPriceDetail = excelService.uploadExcelToMysql(stockPriceDetail);
            JSONObject dataJson = new JSONObject();
            dataJson.put("companyName", retStockPriceDetail.getCompanyCode());
            dataJson.put("stockExchange", retStockPriceDetail.getStockExchange());
            dataJson.put("NoOfRecordsImported", excelStockData.size());
            if(count == 0 || count == excelStockData.size() - 1){
                dataJson.put("date", retStockPriceDetail.get_date());
                dataJson.put("time", retStockPriceDetail.get_time());
                jsonArray.add(dataJson);
            }
            count++;
        }
        jsonObject.put("summaryOfUpload", jsonArray);
        return jsonObject;
    }
}
