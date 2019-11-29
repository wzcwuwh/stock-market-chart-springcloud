package com.ibm.fullstack.service.impl;

import com.ibm.fullstack.dao.CompanyDao;
import com.ibm.fullstack.entity.Company;
import com.ibm.fullstack.service.ICompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private CompanyDao companyDao;

    public CompanyService(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    public List<Company> getCompanyList() {
        return this.companyDao.findAll();
    }

    @Override
    public Company createNewCompany(Company company) {
        return companyDao.save(company);
    }
}