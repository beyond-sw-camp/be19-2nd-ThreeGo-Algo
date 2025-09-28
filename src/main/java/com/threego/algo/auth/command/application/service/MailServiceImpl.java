package com.threego.algo.auth.command.application.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    private final String senderEmail;

    private static int number;

    public MailServiceImpl(JavaMailSender javaMailSender,
                           @Value("${spring.mail.username}") String senderEmail) {
        this.javaMailSender = javaMailSender;
        this.senderEmail = senderEmail;
    }

    // 랜덤 인증번호 생성
    public static void createNumber() {
        number = (int)(Math.random() * 900000) + 100000; // 6자리 랜덤 숫자
    }

    public MimeMessage createMail(String mail) {
        createNumber();
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("Algo 이메일 인증");
            String body = "<h3>Algo 회원가입에 필요한 인증번호는</h3>"
                    + "<h1>" + number + "</h1>"
                    + "<h3>입니다. 감사합니다.</h3>";
            message.setText(body, "UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }

    @Override
    public int sendMail(String mail) {
        MimeMessage message = createMail(mail);
        javaMailSender.send(message);
        return number;
    }
}
