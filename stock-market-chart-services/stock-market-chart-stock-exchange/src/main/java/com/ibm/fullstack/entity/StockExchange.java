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
@Table(name = "stock_exchange")
public class StockExchange implements Serializable {

    private static final long serialVersionUID = 3604781416407830229L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_exchange_id", nullable = false)
    private Long stockExchangeId;

    @Basic
    @Column(name = "stock_exchange_name", nullable = false)
    private String stockExchangeName;

    @Basic
    @Column(name = "brief", nullable = false)
    private String brief;

    @Basic
    @Column(name = "contact_addr", nullable = false)
    private String contactAddr;

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
