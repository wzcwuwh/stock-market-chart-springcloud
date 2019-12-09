package com.ibm.fullstack.entity;

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
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "IPOs_planned")
public class IPOsPlanned implements Serializable {

    private static final long serialVersionUID = 1348304742173556256L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IPO_id", nullable = false)
    private Long IPOId;

    @Basic
    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Basic
    @Column(name = "stock_exchange", nullable = false)
    private String stockExchange;

    @Basic
    @Column(name = "price_per_share", nullable = false)
    private BigDecimal pricePerShare;

    @Basic
    @Column(name = "total_no_of_shares", nullable = false)
    private Integer totalNoOfShares;

    @Basic
    @Column(name = "open_date_time", nullable = false)
    private Date openDateTime;

    @Basic
    @Column(name = "remarks")
    private String remarks;

    @Basic
    @Column(name = "create_date", nullable = false)
    @CreatedDate
    private Date createDate;

    @Basic
    @Column(name = "update_date", nullable = false)
    @LastModifiedDate
    private Date updateDate;
}
