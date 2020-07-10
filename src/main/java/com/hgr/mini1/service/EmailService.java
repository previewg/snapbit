package com.hgr.mini1.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class EmailService extends Authenticator {

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {


        return new PasswordAuthentication(
                "yh9407@gmail.com", "piwjqnlnzwwybrjm");
    }

    public void sendMail(String to, String subject, String content, EmailService smtp) {

        Properties p = new Properties();
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.port", "465");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.debug", "true");
        p.put("mail.smtp.socketFactory.port", "465");
        p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.put("mail.smtp.socketFactory.fallback", "false");
        try {
            Session ses = Session.getInstance(p, smtp);
            ses.setDebug(true);
            MimeMessage msg = new MimeMessage(ses); // 메일의 내용을 담을 객체
            msg.setSubject(subject); // 제목
            InternetAddress adress = new InternetAddress("yh9407@gmail.com", "SNAPBIT");
            msg.setFrom(adress); // 보내는 사람
            Address toAddr = new InternetAddress(to);
            msg.addRecipient(Message.RecipientType.TO, toAddr); // 받는 사람
            msg.setContent(content, "text/html;charset=UTF-8"); // 내용과 인코딩
            Transport.send(msg); // 전송

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}