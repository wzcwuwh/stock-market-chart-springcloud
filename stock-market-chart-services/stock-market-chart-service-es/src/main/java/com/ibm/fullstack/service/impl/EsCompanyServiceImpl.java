package com.ibm.fullstack.service.impl;

import com.ibm.fullstack.dao.EsCompanyDao;
import com.ibm.fullstack.domain.EsCompany;
import com.ibm.fullstack.service.EsCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsCompanyServiceImpl implements EsCompanyService {

    @Autowired
    private EsCompanyDao esCompanyDao;

    @Override
    public List<EsCompany> search(String companyName, String boardChairman, String briefWriteup) {
        return esCompanyDao.findByCompanyNameOrBoardChairmanOrBriefWriteup(companyName, boardChairman, briefWriteup);
    }
}
