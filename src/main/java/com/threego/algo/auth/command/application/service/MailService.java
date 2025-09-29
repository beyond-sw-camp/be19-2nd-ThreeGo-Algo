package com.threego.algo.auth.command.application.service;

import jakarta.mail.MessagingException;

public interface MailService {
    int sendMail(String email) throws MessagingException;
}
