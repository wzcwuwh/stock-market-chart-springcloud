package com.ibm.fullstack.service.impl;

import com.ibm.fullstack.dto.MailBean;
import com.ibm.fullstack.service.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.File;

import static com.ibm.fullstack.dto.MailBean.MAIL_SENDER;

@Service
@Slf4j
public class MailService implements IMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendSimpleMail(MailBean mailBean) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //sender
            simpleMailMessage.setFrom(MAIL_SENDER);
            //receiver
            simpleMailMessage.setTo(mailBean.getReceiver());
            //subject
            simpleMailMessage.setSubject(mailBean.getSubject());
            //content
            simpleMailMessage.setText(mailBean.getContent());
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            log.error("fail to send simple mail by : {}", e.getMessage());
        }
    }

    @Override
    public void sendHTMLMail(MailBean mailBean, String html) {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(MAIL_SENDER);
            mimeMessageHelper.setTo(mailBean.getReceiver());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            StringBuilder sb = new StringBuilder();
            mimeMessageHelper.setText(html, true);
            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            log.error("fail to send HTML mail by : {}", e.getMessage());
        }
    }

    @Override
    public void sendAttachmentMail(MailBean mailBean, String path) {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(MAIL_SENDER);
            mimeMessageHelper.setTo(mailBean.getReceiver());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            mimeMessageHelper.setText(mailBean.getContent());
            //文件路径
            FileSystemResource file = new FileSystemResource(new File(path));
            mimeMessageHelper.addAttachment("test.png", file);

            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            log.error("fail to send attachment mail", e.getMessage());
        }
    }

    @Override
    public void sendTemplateMail(MailBean mailBean, String templateName, Context context) {
        String emailContent = templateEngine.process(templateName, context);
        sendHTMLMail(mailBean,emailContent);
    }
}
