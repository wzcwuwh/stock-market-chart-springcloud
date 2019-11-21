package com.ibm.fullstack.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "stock_price_detail")
public class StockPriceDetail implements Serializable {

    private static final long serialVersionUID = 8679559837314935658L;

    @Basic
    @Column(name = "company_code", nullable = false)
    private String companyCode;

    @Basic
    @Column(name = "stock_exchange", nullable = false)
    private String stockExchange;

    @Basic
    @Column(name = "current_price", nullable = false)
    private Integer currentPrice;

    @Basic
    @Column(name = "_date", nullable = false)
    private Date _date;

    @Basic
    @Column(name = "_time", nullable = false)
    private Date _time;

    @Basic
    @Column(name = "create_date", nullable = false)
    @CreatedDate
    private Date createDate;

    @Basic
    @Column(name = "update_date", nullable = false)
    @LastModifiedDate
    private Date updateDate;
}
