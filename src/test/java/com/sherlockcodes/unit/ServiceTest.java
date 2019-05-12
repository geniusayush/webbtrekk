package com.sherlockcodes.unit;

import com.sherlockcodes.webbtrekk.entity.Email;
import com.sherlockcodes.webbtrekk.service.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.retry.support.RetryTemplate;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.net.Authenticator;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {
    private static final Logger logger = LogManager.getLogger(ServiceTest.class);
    @Mock
    private
    JavaMailSender sender;
    @Mock
    private
    RetryTemplate template = new RetryTemplate();

    private EmailService service;

    @Before
    public void setup() {
        service = new EmailService(sender, template);
    }

    @Test()
    public void basicSetup() throws MessagingException {
        Email email = new Email("wrg", "wgf", "sdger@gmail.com", "");
        final Properties props = new Properties();
        props.put("mail.smtp.host", "");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        final Authenticator auth = null;
        final MimeMessage msg = new MimeMessage(Session.getDefaultInstance(props, null));
        Mockito.when(sender.createMimeMessage()).thenReturn(msg);
        service.sendEmail(email);
        assertEquals(msg.getSubject(), ("wrg"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullpointerSubjectTest() throws MessagingException {
        Email email = new Email(null, "wgf", "sdger@gmail.com", "");
        final Properties props = new Properties();
        props.put("mail.smtp.host", "");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        final Authenticator auth = null;
        final MimeMessage msg = new MimeMessage(Session.getDefaultInstance(props, null));
        Mockito.when(sender.createMimeMessage()).thenReturn(msg);
        service.sendEmail(email);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullpointerbodyTest() throws MessagingException {
        Email email = new Email("aseebg", null, "sdger@gmail.com", "");
        final Properties props = new Properties();
        props.put("mail.smtp.host", "");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        final Authenticator auth = null;
        final MimeMessage msg = new MimeMessage(Session.getDefaultInstance(props, null));
        Mockito.when(sender.createMimeMessage()).thenReturn(msg);
        service.sendEmail(email);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullpointerEmailTest() throws MessagingException {
        Email email = new Email("fdhdth", "wgf", null, "");
        final Properties props = new Properties();
        props.put("mail.smtp.host", "");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        final Authenticator auth = null;
        final MimeMessage msg = new MimeMessage(Session.getDefaultInstance(props, null));
        Mockito.when(sender.createMimeMessage()).thenReturn(msg);
        service.sendEmail(email);
    }

}
