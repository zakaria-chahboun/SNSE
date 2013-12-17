package snse;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author user
 */
public class Send_Mail_Yahoo {

    public Send_Mail_Yahoo(String recipientEmailAddress, String subject,
            String bodyMsg, final String username, final String password, String host) {



        Transport transport = null;

        try {
            final InternetAddress sender = new InternetAddress(username);
            final InternetAddress recipient = new InternetAddress(recipientEmailAddress);

            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(properties);
            session.setDebug(true);

            MimeMessage message = new MimeMessage(session);
            message.setSubject(subject);
            message.setText(bodyMsg);
            message.setFrom(sender);
            message.addRecipient(Message.RecipientType.TO, recipient);
            message.saveChanges();

            transport = session.getTransport("smtp");
            transport.connect(host, sender.getAddress(), password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {
            try {
                transport.close();
            } catch (Exception e) {
            }
        }

    }
}
