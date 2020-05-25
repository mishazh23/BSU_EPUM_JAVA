package util;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtil {
    public static void send(String to, String sub, String msg, final String userName, final String password) {
    String host = "smtp.gmail.com";
    Properties props = new Properties();

    props.put("mail.smtp.host", host );
    props.put("mail.smtp.port", "2525");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    Session session = Session.getInstance(props,new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(userName, password);
        }
    });
    try {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(userName));
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
        message.setSubject(sub);
        message.setText(msg);

        Transport.send(message);System.out.println("Sent message successfully....");
    } catch (MessagingException mex) {
        mex.printStackTrace();
    }
}
}