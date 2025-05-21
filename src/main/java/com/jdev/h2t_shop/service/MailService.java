package com.jdev.h2t_shop.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOrderConfirmationEmail(String toEmail, String customerName, int orderId, int price,int total) throws MessagingException {
        String htmlContent = "<html>" +
                "<body>" +
                "<h2>Xin chào " + customerName + ",</h2>" +
                "<p>Cảm ơn bạn đã đặt hàng tại H2T Shop!</p>" +
                "<p><strong>Mã đơn hàng:</strong> " + orderId + "</p>" +
                "<p><strong>Tổng tiền:</strong> " + price + " VNĐ</p>" +
                "<p><strong>Số lượng:</strong> " + total + " </p>" +
                "<p>Chúng tôi sẽ xử lý đơn hàng của bạn trong thời gian sớm nhất.</p>" +
                "<p>Trân trọng,<br/>H2T Shop</p>" +
                "</body>" +
                "</html>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true, "UTF-8");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        helper.setFrom(fromEmail);
        helper.setTo(toEmail);
        helper.setSubject("Xác nhận đơn hàng #" + orderId);
        helper.setText(htmlContent, true); // true = gửi HTML

        mailSender.send(message);
    }
}
