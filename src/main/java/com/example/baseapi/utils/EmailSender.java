package com.example.baseapi.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Component
public class EmailSender {


    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailReinicioPassword(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@inmes.com", "INMES");
        helper.setTo(recipientEmail);

        String subject = "Este es el link para reiniciar tu password";

        String content = "<p>Hola,</p>"
                + "<p>Has solicitado un reinicio de password.</p>"
                + "<p>Click en el siguiente link para reiniciar password:</p>"
                + "<p><a href=\"" + link + "\">Cambiar mi password</a></p>"
                + "<br>"
                + "<p>Ignore este email si usted recuerda su password, "
                + "o si no ha requerido el inicio.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }
}