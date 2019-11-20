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
@Table(name = "_user")
public class User implements Serializable {
    private static final long serialVersionUID = -5301215248787089787L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Basic
    @Column(name = "username", nullable = false)
    private String username;

    @Basic
    @Column(name = "password", nullable = false)
    private String password;

    @Basic
    @Column(name = "contact_no")
    private String contactNo;

    @Basic
    @Column(name = "reg_code")
    private String regCode;

    @Basic
    @Column(name = "_role", nullable = false)
    private String role;

    @Basic
    @Column(name = "linkedin_url")
    private String linkedinUrl;

    @Basic
    @Column(name = "years_of_experience")
    private Float yearsOfExperience;

    @Basic
    @Column(name = "_active")
    private Boolean active;

    @Basic
    @Column(name = "confirmed_signup")
    private Boolean confirmedSignup;

    @Basic
    @Column(name = "reset_pwd", nullable = false)
    private Boolean resetPwd;

    @Basic
    @Column(name = "reset_pwd_date")
    private Date resetPwdDate;

    @Basic
    @Column(name = "create_date", nullable = false)
    @CreatedDate
    private Date createDate;

    @Basic
    @Column(name = "update_date", nullable = false)
    @LastModifiedDate
    private Date updateDate;
}
