package service;

import utils.RandomHelper;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailService {

    public void sendMail(String clientEmail, String randomCode) {
        final String username = "oleg.porohov.92@gmail.com";
        final String password = "ghjcnjgfhjkm123";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("oleg.porohov.92@gmail.com"));

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(clientEmail));
            message.setSubject("Purchase confirmation code");
            message.setText("Your code to by product: " + randomCode);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
