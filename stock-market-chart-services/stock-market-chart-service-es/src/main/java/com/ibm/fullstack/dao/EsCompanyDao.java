package com.ibm.fullstack.dao;

import com.ibm.fullstack.domain.EsCompany;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EsCompanyDao extends ElasticsearchRepository<EsCompany, Long> {

    List<EsCompany> findByCompanyNameOrBoardChairmanOrBriefWriteup(String companyName,
                                                                   String boardChairman,
                                                                   String briefWriteup);
}
