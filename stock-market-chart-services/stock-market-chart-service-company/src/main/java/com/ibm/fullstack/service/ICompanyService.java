package com.ibm.fullstack.service;

import com.ibm.fullstack.entity.Company;

import java.util.List;

public interface ICompanyService {

    List<Company> getCompanyList();

    Company createNewCompany(Company company);
}
