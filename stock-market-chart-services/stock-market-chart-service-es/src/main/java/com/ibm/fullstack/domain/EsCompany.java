package com.ibm.fullstack.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "ibm-stock-market-chart", type = "company", shards = 1, replicas = 0, createIndex = false)
public class EsCompany implements Serializable {

    private static final long serialVersionUID = -7376798562793461848L;

    @Id
    private Long id;

    @Field(type = FieldType.Long)
    private Long companyId;

    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String companyName;

    @Field(type = FieldType.Double)
    private BigDecimal turnover;

    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String CEO;

    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String boardChairman;

    private String listInStockExchanges;

    @Field(type = FieldType.Keyword)
    private String sector;

    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String briefWriteup;

    private String stockCode;
    private String logo;

    @Field(type = FieldType.Date, format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss || yyyy-MM-dd || epoch_millis")
    private Date createDate;
    @Field(type = FieldType.Date, format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss || yyyy-MM-dd || epoch_millis")
    private Date updateDate;
}
