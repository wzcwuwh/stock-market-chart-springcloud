package com.ibm.fullstack.service.impl;

import com.ibm.fullstack.dao.StockExchangeDao;
import com.ibm.fullstack.entity.StockExchange;
import com.ibm.fullstack.service.IStockExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StockExchangeService implements IStockExchangeService {

    @Autowired
    private StockExchangeDao stockExchangeDao;

    public StockExchangeService(StockExchangeDao stockExchangeDao) {
        this.stockExchangeDao = stockExchangeDao;
    }

    @Override
    public List<StockExchange> getStockExchangeList() {
        return this.stockExchangeDao.findAll();
    }

    @Override
    public StockExchange createNewStockExchange(StockExchange stockExchange) {
        return stockExchangeDao.save(stockExchange);
    }
}