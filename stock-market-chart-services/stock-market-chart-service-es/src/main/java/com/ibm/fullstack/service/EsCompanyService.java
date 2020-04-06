package com.ibm.fullstack.service;

import com.ibm.fullstack.domain.EsCompany;

import java.util.List;

public interface EsCompanyService {

    List<EsCompany> search(String companyName,
                           String boardChairman,
                           String briefWriteup);
}
