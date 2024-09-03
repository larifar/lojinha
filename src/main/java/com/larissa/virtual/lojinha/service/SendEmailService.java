package com.larissa.virtual.lojinha.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Service
public class SendEmailService {
    private String userName;
    private String password;

    public SendEmailService() {
        Properties props = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/config.properties");
            props.load(fis);
            this.userName = props.getProperty("email.username");
            this.password = props.getProperty("email.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void sendEmailHtml(String subject, String message, String recipient) throws MessagingException, UnsupportedEncodingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.ssl.trust", "*");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls", "false");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });

        session.setDebug(true);

        Address[] toUser = InternetAddress.parse(recipient);

        Message messageToSend = new MimeMessage(session);
        messageToSend.setFrom(new InternetAddress(userName, "Study Java", "UTF-8"));
        messageToSend.setRecipient(Message.RecipientType.TO, toUser[0]);
        messageToSend.setSubject(subject);
        messageToSend.setContent(message, "text/html; charset=utf-8");

        Transport.send(messageToSend);
    }
}
