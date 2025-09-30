package com.example.backend.service;

import com.example.backend.bussinessObject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("FRESHER");
        mailSender.send(message);
    }

    public void voidResetPasswordEmail(User user, String siteURL) {
        String resetURL = siteURL + "/reset_password?token=" + user.getResetPasswordToken();
        String subject = "Đặt lại mật khẩu";
        String content = "<p>Xin chào, " + user.getName() + "</p>"
                + "Cảm ơn bạn đã sử dụng dịch vụ đặt lại mật khẩu, vui lòng nhấn vào liên kết bên dưới để tiếp tục"
                + "<p><a href=\"" + resetURL + "\">ĐẶT LẠI MẬT KHẨU</a></p>"
                + "<br>"
                + "<p>Nếu bạn không thực hiện yêu cầu này, vui lòng bỏ qua email này.</p>";
        sendMail(user.getEmail(), subject, content);
    }
}
