package com.ibm.fullstack.service.impl;

import com.ibm.fullstack.dao.CompanyDao;
import com.ibm.fullstack.dao.StockPriceDetailDao;
import com.ibm.fullstack.entity.StockPriceDetail;
import com.ibm.fullstack.service.IStockPriceDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StockPriceDetailService implements IStockPriceDetailService {

    @Autowired
    private StockPriceDetailDao stockPriceDetailDao;

    @Autowired
    private CompanyDao companyDao;

    public StockPriceDetailService(StockPriceDetailDao stockPriceDetailDao) {
        this.stockPriceDetailDao = stockPriceDetailDao;
    }

    @Override
    public List<StockPriceDetail> getStockPriceDetails(String companyName, String stockExchange) {
        String stockCode = this.companyDao.findStockCodeByCompanyName(companyName);
        log.info("stock code: {} from company name {}", stockCode, companyName);
        return this.stockPriceDetailDao.findStockPriceDetailByCompanyCodeAndStockExchange(stockCode, stockExchange);
    }
}
