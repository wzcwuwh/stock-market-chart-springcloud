package com.ibm.fullstack.dao;

import com.ibm.fullstack.entity.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockExchangeDao extends JpaRepository<StockExchange, Long> {

}
