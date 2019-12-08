package com.ibm.fullstack.service;

import com.ibm.fullstack.entity.Company;
import com.ibm.fullstack.entity.StockPriceDetail;

import java.util.List;

public interface ICompanyService {

    List<Company> getCompanyList();

    Company createNewCompany(Company company);

    List<Company> searchCompany(String companySearchTxt);

    Company findCompanyNameByStockCode(String stockCode);
}
