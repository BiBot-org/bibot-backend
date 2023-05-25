package com.coderecipe.v1.open.user.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSenderImpl mailSender;
    @Value("${spring.mail.username}")
    private String senderEmail;

    public String joinEmail(String email, int randNum) {
        String setForm = senderEmail;
        String toMail = email;
        String title = "회원가입 인증 이메일입니다.";
        String content = "";
        content += "<div style=\"text-align: center; background-size: cover; position: absolute; \">";
        content += "<img width=\"120\" height=\"120\" style=\"margin: 0 0 32px 0px;padding-right: 30px; padding-left: 30px;\" src=\"https://upload.wikimedia.org/wikipedia/en/thumb/d/d3/Starbucks_Corporation_Logo_2011.svg/800px-Starbucks_Corporation_Logo_2011.svg.png\" loading=\"lazy\">";
        content += "<h1 style=\"color: black; font-size: 30px; padding-right: 30px; padding-left: 30px;\">가입을 환영 합니다.</h1>";
        content += "<p style=\"color: black; font-size: 17px; padding-right: 30px; padding-left: 30px;\">해당 인증번호를 인증번호 확인란에 기입 해 주세요.</p>";
        content +=
                "<h2 style=\"color : black; font-size: 15px; padding-right : 30px; padding-left: 30px;\">"
                        + randNum + "</h2>";
        content += "<a href=\"https://github.com/BiBot-org\" style=\"text-decoration: none; color: black;\" rel=\"noreferrer noopener\" target=\"_blank\">ⓒ 2023 BiBot-org. All Rights Reserved.</a></div>";

        mailSend(setForm, toMail, title, content);

        return Integer.toString(randNum);
    }

    public String mailSend(String setForm, String toMail, String title, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setForm);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return toMail;
    }

}