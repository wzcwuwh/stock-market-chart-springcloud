package com.ibm.fullstack.dao;

import com.ibm.fullstack.entity.Company;
import com.ibm.fullstack.entity.StockPriceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockPriceDetailDao extends JpaRepository<Company, Long> {

    List<StockPriceDetail> findStockPriceDetailByCompanyCodeAndStockExchange(String companyCode, String stockExchange);
}
