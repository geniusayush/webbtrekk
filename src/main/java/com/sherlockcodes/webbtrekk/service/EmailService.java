package com.sherlockcodes.webbtrekk.service;

import com.sherlockcodes.webbtrekk.entity.Email;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailService {
    private static final Logger logger = LogManager.getLogger(EmailService.class);
    private final RetryTemplate retryTemplate;


    private final JavaMailSender sender;

    @Autowired
    public EmailService(final JavaMailSender sender, final RetryTemplate temp) {
        this.sender = sender;
        this.retryTemplate = temp;
    }

    public void sendEmail(Email email) {
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper;
            if (email.getAttatchment().isEmpty())
                helper = new MimeMessageHelper(message);
            else helper = new MimeMessageHelper(message, true);
            helper.setTo(email.getTo());
            helper.setText(email.getBody());
            helper.setSubject(email.getSubject());
            if (!email.getAttatchment().isEmpty())
                helper.addAttachment("Attachment", () -> new UrlResource(email.getAttatchment()).getInputStream());
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        sendEmail(message);
    }

    @Async
    private void sendEmail(MimeMessage message) {
        retryTemplate.execute(arg0 -> {
            sender.send(message);
            return null;
        });
    }


}