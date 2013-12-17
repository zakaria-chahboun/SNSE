package snse;

import java.io.IOException;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Header;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

public class Mail_Box {

    public static Properties props;
    public static Store store;
    public static Session session;
    public static Message messages[];
    public static Folder inbox;
    public static String email, password;

    public static boolean connection(String email, String password) {
        //il perment la connection a la boit mail
        String serv = server_mail(email);
        Mail_Box.email = email;
        Mail_Box.password = password;
        props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");

        session = Session.getInstance(props, null);
        try {
            store = session.getStore("imaps");
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(Mail_Box.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            store.connect(serv, email, password);
        } catch (MessagingException ex) {

            Logger.getLogger(Mail_Box.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static boolean deconnection() {
        try {

            if (Mail_Box.inbox != null) {
                Mail_Box.inbox.close(true);
            }
            if (Mail_Box.store != null) {
                Mail_Box.store.close();
            }
        } catch (MessagingException ex) {

            Logger.getLogger(Mail_Box.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

    public static boolean check_email() {
//         vérifier si il y a un nouveau message
        try {
            try {
                inbox = store.getFolder("Inbox");
            } catch (MessagingException ex) {
                Logger.getLogger(Mail_Box.class.getName()).log(Level.SEVERE, null, ex);
            }
            inbox.open(Folder.READ_ONLY);
            //Message messages[] = inbox.getMessages();
            FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
            messages = inbox.search(ft);

        } catch (MessagingException ex) {
            Logger.getLogger(Mail_Box.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (messages.length > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean autochek_email() {




        String log = "";
        log = Mail_Box.email;

        String pass = "";
        pass = Mail_Box.password;

        if (Mail_Box.connection(log, pass)) {



            if (Mail_Box.check_email()) {
                Message[] messages = Mail_Box.alert_mail();
                int notip = (messages.length - Mail_Box.nbr_message_lu_or_inde(messages)) * 100;
                for (int i = 0; i < messages.length; i++) {
                    Message m = messages[i];
                    String ID_mail = null;
                    //--------------------recupiration de l'Id d'un mail-----------------------------------------------------------------------
                    try {



                        Enumeration headers = m.getAllHeaders();
                        while (headers.hasMoreElements()) {
                            Header h = (Header) headers.nextElement();

                            if (h.getName().equals("Message-ID")) {
                                ID_mail = h.getValue();
                            }
                        }



                    } catch (Exception e) {
                    }

                    //------------------------recuperation de l'expiditeur-------------------------------------------------------------------           
                    String mailind = null;
                    Address[] addd;
                    try {
                        if ((addd = m.getFrom()) != null) {
                            for (int j = 0; j < addd.length; j++) {
                                mailind = addd[j].toString();
                            }
                        }
                    } catch (MessagingException ex) {
                        Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //-------------------------------------------------------------------------------------------  
                    ArrayList<String> MailInd = Enregitrer.Dser_MailInd();

                    if (!MailInd.contains(mailind)) {

                        ArrayList<String> arry = Enregitrer.Dser_MailLu();
                        if (!arry.contains(ID_mail)) {
                            Address[] a;
                            try {
                                if ((a = m.getFrom()) != null) {
                                    for (int j = 0; j < a.length; j++) {
                                        notip = (notip - 100);
                                        int notipo = notip;
//                %(scrSize.height - toolHeight.bottom - this.getHeight());
                                        final Notification n = new Notification(notipo);
                                        n.M = m;

                                        Date d = m.getReceivedDate();
//              System.out.println("date de receprion"+d);
                                        n.jLabel4.setText(m.getSubject());
                                        n.jLabel3.setText(a[j].toString());


//           System.out.println(n.notipos);

                                        n.setVisible(true);
                                        new Thread() {

                                            @Override
                                            public void run() {
                                                try {
                                                    Thread.sleep(50000); // time after which pop up will be disappeared.
                                                    n.dispose();
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        ;
                                    }


                                
                             .start();  
      
            }
                
                 } } catch (MessagingException ex) {
                                Logger.getLogger(Mail_Box.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            notip = notip - 100;
                        }
                    } else {
                        notip = notip - 100;
                    }


                }


            }


        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Le nom d'utilisateur ou le mot de passe saisi est incorrect");
        }


        return true;
    }

    public static String server_mail(String email) {
        //c'est une fonction qui determine qu'il serveur de messagerie a été utiliser et retourn la synstax du protocole imape concerner sinon null
        String em = email.substring(email.indexOf('@') + 1);
        Map<String, String> map = new HashMap<String, String>();
        map.put("yahoo.fr", "imap.mail.yahoo.com");
        map.put("gmail.com", "imap.gmail.com");
        map.put("myopera.com", "imap.myopera.com");
        map.put("neuf.fr", " imap.neuf.fr");
        map.put("aliceadsl.fr", "imap.aliceadsl.fr");
        map.put("orange.fr", "imap.orange.fr");
        map.put("numericable.fr", "imap.numericable.fr");
        return map.get(em);
    }

    public static Message[] alert_mail() {

        return messages;

    }

    public static String[] subject(Message M) {
        Address[] a;
        String tab[] = new String[100];
        try {
            if ((a = M.getFrom()) != null) {
                for (int j = 0; j < a.length; j++) {

                    tab[j] = M.getSubject();



                }
            }

        } catch (MessagingException ex) {
            Logger.getLogger(Mail_Box.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return tab;

    }

    public static String[] destination(Message M) {
        Address[] a;
        String tab[] = new String[100];
        try {
            if ((a = M.getRecipients(Message.RecipientType.TO)) != null) {
                for (int j = 0; j < a.length; j++) {
                    tab[j] = a[j].toString();
                }

            }
        } catch (MessagingException ex) {
            Logger.getLogger(Mail_Box.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tab;


    }

    public static String[] source(Message M) {
        Address[] a;
        String tab[] = new String[100];
        try {
            if ((a = M.getFrom()) != null) {
                for (int j = 0; j < a.length; j++) {



                    tab[j] = a[j].toString();


                }
            }
        } catch (MessagingException ex) {
            Logger.getLogger(Mail_Box.class.getName()).log(Level.SEVERE, null, ex);
        }


        return tab;


    }

    public String body(Message M) {
        Multipart mp;
        String q = null;
        try {
            mp = (Multipart) M.getContent();
            Object p = mp.getBodyPart(0).getContent();
            q = p.toString();//object has the body content  
            //   System.out.println(q);//prints the body  


        } catch (IOException ex) {
            Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
        }
        return q;
    }

    public static void mafhamtwalo(Message M) {


        try {
            Address[] a;
            String tab[] = new String[100];
            if ((a = M.getFrom()) != null) {
                for (int j = 0; j < a.length; j++) {
                    tab[j] = M.getSubject();

                }

            }
//            System.out.println("\nsujet:----------------\n" + tab[0]);
        } catch (MessagingException ex) {
            Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Address[] a;
            String tab[] = new String[100];
            if ((a = M.getFrom()) != null) {
                for (int j = 0; j < a.length; j++) {
                    tab[j] = a[j].toString();
                }
            }
//            System.out.println("\nsource:----------------\n" + tab[0]);
        } catch (MessagingException ex) {
            Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Address[] a;
            String tab[] = new String[100];
            if ((a = M.getRecipients(Message.RecipientType.TO)) != null) {
                for (int j = 0; j < a.length; j++) {
                    tab[j] = a[j].toString();
                }
            }
//            System.out.println("\ndestination:----------------\n" + tab[0]);
        } catch (MessagingException ex) {
            Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public static boolean send_message(String sendTo[], String emailSubjectTxt,
            String emailMsgTxt, String emailFromAddress) {
        String serv = server_mail(Mail_Box.email);
//     String serv=server_mail("test.pfc.zf@gmail.com");
        if (!serv.equals("imap.mail.yahoo.com")) {

//       System.out.println("-");    
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            SendMailThroughJava sendMailThroughJava = new SendMailThroughJava();
            try {
                sendMailThroughJava.sendSSLMessage(sendTo, emailSubjectTxt, emailMsgTxt, emailFromAddress, Mail_Box.email, Mail_Box.password, serv);
//            sendMailThroughJava.sendSSLMessage(tab,"subject","exxt", "zakaria.ouhrochan@gmail.com","test.pfc.zf@gmail.com","Pass1word","smtp.gmail.com");
            } catch (MessagingException ex) {
                Logger.getLogger(Mail_Box.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            serv = "smtp.mail.yahoo.com";
            for (int i = 0; i < sendTo.length; i++) {
                String destination_Add = sendTo[i];
                Send_Mail_Yahoo S = new Send_Mail_Yahoo(destination_Add, emailSubjectTxt, emailMsgTxt, Mail_Box.email, Mail_Box.password, serv);
            }

        }


        return true;

    }

    public static int nbr_message_lu_or_inde(Message messages1[]) {
        int count = 0;
        for (int i = 0; i < messages1.length; i++) {
            Message m = messages1[i];
            String ID_mail = null;
            //--------------------recupiration de l'Id d'un mail-----------------------------------------------------------------------
            try {



                Enumeration headers = m.getAllHeaders();
                while (headers.hasMoreElements()) {
                    Header h = (Header) headers.nextElement();

                    if (h.getName().equals("Message-ID")) {
                        ID_mail = h.getValue();
                    }
                }



            } catch (Exception e) {
            }

            //------------------------recuperation de l'expiditeur-------------------------------------------------------------------           
            String mailind = null;
            Address[] addd;
            try {
                if ((addd = m.getFrom()) != null) {
                    for (int j = 0; j < addd.length; j++) {
                        mailind = addd[j].toString();
                    }
                }
            } catch (MessagingException ex) {
                Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
            }

            //-------------------------------------------------------------------------------------------  
            ArrayList<String> MailInd = Enregitrer.Dser_MailInd();

            if (MailInd.contains(mailind)) {
                count++;
            } else {
                ArrayList<String> arry = Enregitrer.Dser_MailLu();
                if (arry.contains(ID_mail)) {
                    count++;
                }
            }

        }
        return count;
    }
}
