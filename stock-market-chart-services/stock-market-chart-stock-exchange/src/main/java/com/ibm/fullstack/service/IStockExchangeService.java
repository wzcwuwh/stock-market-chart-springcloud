package com.ibm.fullstack.service;

import com.ibm.fullstack.entity.StockExchange;

import java.util.List;

public interface IStockExchangeService {

    List<StockExchange> getStockExchangeList();

    StockExchange createNewStockExchange(StockExchange company);
}
