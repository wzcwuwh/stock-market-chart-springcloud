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
@Table(name = "company")
public class Company implements Serializable {

    private static final long serialVersionUID = 4290111083834660897L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @Basic
    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Basic
    @Column(name = "turnover", nullable = false)
    private Integer turnover;

    @Basic
    @Column(name = "CEO", nullable = false)
    private String CEO;

    @Basic
    @Column(name = "board_of_directors")
    private String boardOfDirectors;

    @Basic
    @Column(name = "listed_in_stock_exchanges")
    private String listInStockExchanges;

    @Basic
    @Column(name = "sector")
    private String sector;

    @Basic
    @Column(name = "brief_writeup")
    private String briefWriteup;

    @Basic
    @Column(name = "stock_code", nullable = false)
    private String stockCode;

    @Basic
    @Column(name = "logo_path")
    private String logoPath;

    @Basic
    @Column(name = "create_date", nullable = false)
    @CreatedDate
    private Date createDate;

    @Basic
    @Column(name = "update_date", nullable = false)
    @LastModifiedDate
    private Date updateDate;
}
