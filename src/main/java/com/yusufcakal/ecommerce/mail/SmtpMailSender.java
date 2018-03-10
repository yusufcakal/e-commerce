package com.yusufcakal.ecommerce.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import com.yusufcakal.ecommerce.model.User;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SmtpMailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    private Configuration freemarkerConfig;

    public void send(User user) throws MessagingException {

        freemarkerConfig = new Configuration();

        MimeMessage message = javaMailSender.createMimeMessage();

        String content = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<title></title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "Merhaba "+ user.getName() +" kayıt işlemini <a href=\"http://localhost:8080/user/verify/" + user.getToken() + "\"> buradan </a> doğrulayabilrisin.\n" +
                "\n" +
                "</body>\n" +
                "</html>";

        MimeMessageHelper helper = new MimeMessageHelper(message, false); // false indicates
        // multipart message
        helper.setSubject("Kullanıcı Doğrulama");
        helper.setTo(user.getEmail());
        helper.setText(content, true); // true indicates html

        javaMailSender.send(message);

    }

}
