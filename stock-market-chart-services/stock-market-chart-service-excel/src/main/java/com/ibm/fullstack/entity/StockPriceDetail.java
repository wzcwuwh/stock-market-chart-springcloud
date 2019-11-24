package com.ibm.fullstack.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
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

    private static final long serialVersionUID = -7702364735982217283L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_price_detail_id", nullable = false)
    private Long stockPriceDetailId;

    @Excel(name = "Company Code", orderNum = "0")
    @Basic
    @Column(name = "company_code", nullable = false)
    private String companyCode;

    @Excel(name = "Stock Exchange", orderNum = "1")
    @Basic
    @Column(name = "stock_exchange", nullable = false)
    private String stockExchange;

    @Excel(name = "Price Per Share(in Rs)", orderNum = "2")
    @Basic
    @Column(name = "current_price", nullable = false)
    private Integer currentPrice;

    @Excel(name = "Date", orderNum = "3")
    @Basic
    @Column(name = "_date", nullable = false)
    private Date _date;

    @Excel(name = "Time", orderNum = "4")
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
