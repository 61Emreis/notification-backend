package com.example.notification.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageReceiver {

    @Autowired
    private MailService mailService;

    @RabbitListener(queues = "personnel.queue")
    public void receiveMessage(String message) {
        System.out.println("Received Message: " + message);

        // Mesaj alındığında e-posta gönderme işlemi
        String to = "recipient@example.com";  // Gönderilecek e-posta adresi
        String subject = "Personnel Update";
        String text = "New personnel change: " + message;
        mailService.sendMail(to, subject, text);
    }
}
