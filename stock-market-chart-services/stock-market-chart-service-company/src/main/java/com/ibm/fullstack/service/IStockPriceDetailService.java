package com.ibm.fullstack.service;

import com.ibm.fullstack.entity.StockPriceDetail;

import java.util.List;

public interface IStockPriceDetailService {

    List<StockPriceDetail> getStockPriceDetails(String companyName, String stockExchange);
}
