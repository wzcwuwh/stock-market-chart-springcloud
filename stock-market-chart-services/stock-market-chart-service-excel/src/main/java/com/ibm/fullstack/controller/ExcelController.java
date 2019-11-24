package com.ibm.fullstack.controller;

import com.ibm.fullstack.entity.StockPriceDetail;
import com.ibm.fullstack.service.IExcelService;
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
    public void importExcel(@RequestPart(value = "file", required = false) MultipartFile file){
        log.info(String.valueOf(file.isEmpty()));
        log.info("received filePath: {}", file.getOriginalFilename());
        List<StockPriceDetail> stockPriceDetails = excelService.importExcel(file, null, null, StockPriceDetail.class);
        log.info(String.valueOf(stockPriceDetails.size()));
    }
}
