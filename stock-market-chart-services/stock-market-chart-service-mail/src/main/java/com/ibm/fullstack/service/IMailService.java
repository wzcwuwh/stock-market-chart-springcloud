package com.ibm.fullstack.service;

import com.ibm.fullstack.dto.MailBean;
import org.thymeleaf.context.Context;

public interface IMailService {

    public void sendSimpleMail(MailBean mailBean);

    public void sendHTMLMail(MailBean mailBean, String html);

    public void sendAttachmentMail(MailBean mailBean, String path);

    public void sendTemplateMail(MailBean mailBean, String templateName, Context context);
}
