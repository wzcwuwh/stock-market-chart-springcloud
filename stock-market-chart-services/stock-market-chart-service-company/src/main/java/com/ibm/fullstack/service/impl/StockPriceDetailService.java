package com.ibm.fullstack.service.impl;

import com.ibm.fullstack.dao.CompanyDao;
import com.ibm.fullstack.dao.StockPriceDetailDao;
import com.ibm.fullstack.entity.Company;
import com.ibm.fullstack.entity.StockPriceDetail;
import com.ibm.fullstack.service.IStockPriceDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StockPriceDetailService implements IStockPriceDetailService {

    @Autowired
    private StockPriceDetailDao stockPriceDetailDao;

    @Autowired
    private CompanyDao companyDao;

    public StockPriceDetailService(StockPriceDetailDao stockPriceDetailDao, CompanyDao companyDao) {
        this.stockPriceDetailDao = stockPriceDetailDao;
        this.companyDao = companyDao;
    }

    @Override
    public List<StockPriceDetail> getStockPriceDetails(String companyName, String stockExchange, Date startDate, Time startTime, Date endDate, Time endTime) {
        Company company = this.companyDao.findStockCodeByCompanyName(companyName);
        String stockCode = company.getStockCode();
        log.info("stock code: {} from company name {}", stockCode, companyName);
        return this.stockPriceDetailDao.findStockPriceDetailByCondition(stockCode, stockExchange, startDate, startTime, endDate, endTime);
    }

    @Override
    public List<StockPriceDetail> retrieveStockPriceDetails(String companyName, String stockExchange) {
        Company company = this.companyDao.findStockCodeByCompanyName(companyName);
        String stockCode = company.getStockCode();
        log.info("stock code: {} from company name {}", stockCode, companyName);
        return this.stockPriceDetailDao.findStockPriceDetailByCompanyCodeAndStockExchange(stockCode, stockExchange);
    }

    @Override
    public BigDecimal findCurrentPriceByCompanyCode(String companyCode) {
        List<StockPriceDetail> stockPriceDetails = stockPriceDetailDao.findCurrentPriceByCompanyCode(companyCode);
        Optional<StockPriceDetail> optionalStockPriceDetail = stockPriceDetails.
                stream().
                sorted(Comparator.comparing(StockPriceDetail::get_date).reversed().
                        thenComparing(StockPriceDetail::get_time).reversed()).
                findFirst();
        if(optionalStockPriceDetail.isPresent()){
            return optionalStockPriceDetail.get().getCurrentPrice();
        }
        return null;
    }
}
