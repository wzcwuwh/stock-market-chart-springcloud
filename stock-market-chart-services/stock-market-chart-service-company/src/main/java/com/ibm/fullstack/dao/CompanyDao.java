package com.ibm.fullstack.dao;

import com.ibm.fullstack.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CompanyDao extends JpaRepository<Company, Long> {

    @Query(nativeQuery = true, value = "select * from ibm_stock_market_chart.company where company_name like %:companySearchTxt%" )
    List<Company> findCompanyByCompanySearchTxt(@Param("companySearchTxt") String companySearchTxt);
}
