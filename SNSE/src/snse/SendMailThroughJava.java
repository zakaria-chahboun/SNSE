package snse;

import java.security.Security;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/** 
 * @author www.javaworkspace.com 
 */
public class SendMailThroughJava {

//    private static final String SMTP_HOST_NAME = "smtp.gmail.com";  
    private static final String SMTP_PORT = "465";
    private static final String emailMsgTxt = "Welcome to www.javaworkspace.com";
    private static final String emailSubjectTxt = "A test mail from www.javaworkspace.com";
    private static final String emailFromAddress = "fatimaezzahra.ghazzali@gmail.com";
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private static final String[] sendTo = {"zakaria.ouhrochan@gmail.com"};

    public static void main(String args[]) throws Exception {

        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        SendMailThroughJava sendMailThroughJava = new SendMailThroughJava();
        sendMailThroughJava.sendSSLMessage(sendTo, emailSubjectTxt,
                emailMsgTxt, emailFromAddress, "test.pfc.zf@gmail.com", "Pass1word", "smtp.gmail.com");
        System.out.println("Sucessfully sent mail to all Users");
    }

    /** 
     * @param recipients 
     * @param subject 
     * @param message 
     * @param from 
     * @throws MessagingException 
     */
    public void sendSSLMessage(String recipients[], String subject,
            String message, String from, final String adressesrc, final String password, String SMTP_HOST_NAME) throws MessagingException {
        boolean debug = true;

        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {

                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(adressesrc,
                                password);
                    }
                });

        session.setDebug(debug);

        Message msg = new MimeMessage(session);
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        InternetAddress[] addressTo = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++) {
            addressTo[i] = new InternetAddress(recipients[i]);
        }
        msg.setRecipients(Message.RecipientType.TO, addressTo);

        // Setting the Subject and Content Type  
        msg.setSubject(subject);
        msg.setContent(message, "text/plain");
        Transport.send(msg);
    }
}
