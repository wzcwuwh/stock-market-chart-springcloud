package com.ibm.fullstack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailBean implements Serializable {

    public static final String MAIL_SENDER = "wzcwuwh@163.com";

    private String receiver;
    private String subject;
    private String content;
}
