package com.ibm.fullstack.dao;

import com.ibm.fullstack.entity.StockPriceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelDao extends JpaRepository<StockPriceDetail, Long> {
}
