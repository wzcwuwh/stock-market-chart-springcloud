package com.ibm.fullstack.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelStockData implements Serializable {

    private static final long serialVersionUID = 3779555652312894844L;

    @Excel(name = "Company Code", orderNum = "0")
    private String companyCode;

    @Excel(name = "Stock Exchange", orderNum = "1")
    private String stockExchange;

    @Excel(name = "Price Per Share(in Rs)", orderNum = "2")
    private String currentPrice;

    @Excel(name = "Date", orderNum = "3")
    private String _date;

    @Excel(name = "Time", orderNum = "4")
    private String _time;
}
