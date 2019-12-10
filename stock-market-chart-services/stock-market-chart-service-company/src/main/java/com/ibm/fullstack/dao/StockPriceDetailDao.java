package com.ibm.fullstack.dao;

import com.ibm.fullstack.entity.StockPriceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface StockPriceDetailDao extends JpaRepository<StockPriceDetail, Long> {

    @Query(nativeQuery = true, value = "select * from ibm_stock_market_chart.stock_price_detail " +
            "where company_code = :companyCode " +
            "and stock_exchange = :stockExchange " +
            "and (_date between :startDate and :endDate) " +
            "and (_time between :startTime and :endTime) ")
    List<StockPriceDetail> findStockPriceDetailByCondition(@Param("companyCode") String companyCode,
                                                                             @Param("stockExchange") String stockExchange,
                                                                             @Param("startDate") Date startDate,
                                                                             @Param("startTime") Time startTime,
                                                                             @Param("endDate") Date endDate,
                                                                             @Param("endTime") Time endTime);

    List<StockPriceDetail> findStockPriceDetailByCompanyCodeAndStockExchange(String companyCode, String stockExchange);

    List<StockPriceDetail> findCurrentPriceByCompanyCode(String companyCode);
}
