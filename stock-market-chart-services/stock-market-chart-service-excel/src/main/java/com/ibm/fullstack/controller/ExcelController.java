package com.ibm.fullstack.controller;

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
    public void importExcel(@RequestParam(value = "file") MultipartFile file){
        List<ExcelStockData> excelStockData = excelService.importExcel(file, 0, 1, ExcelStockData.class);
        for(ExcelStockData esd: excelStockData){
            StockPriceDetail stockPriceDetail = ExcelDataToMysqlData.transform(esd);
            System.out.println(stockPriceDetail.getCompanyCode());
            System.out.println(stockPriceDetail.getStockExchange());
            System.out.println(stockPriceDetail.getCurrentPrice());
            System.out.println(stockPriceDetail.get_date());
            System.out.println(stockPriceDetail.get_time());
        }
    }
}
