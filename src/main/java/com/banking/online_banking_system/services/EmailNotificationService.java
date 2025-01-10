package com.banking.online_banking_system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.InternetAddress;

@Service
public class EmailNotificationService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendTransactionNotification(String toEmail, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            message.setFrom(new InternetAddress("nikhilareddy5005@gmail.com", "RBI")); // Custom Bank Name
            message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
