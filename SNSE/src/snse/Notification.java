/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Notification.java
 *
 * Created on Apr 28, 2012, 9:51:35 PM
 */
package snse;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.logging.*;
import javax.mail.*;

/**
 *
 * @author user
 */
public class Notification extends javax.swing.JFrame {

    public int notipos;
    public String source, sujet;
    public Message M;

    /** Creates new form Notification */
    public Notification(int noti) {

        this.setUndecorated(true);

        initComponents();
        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();// size of the screen
        Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(this.getGraphicsConfiguration());// height of the task bar
        notipos = noti;
        this.setLocation(scrSize.width - this.getWidth(), scrSize.height - toolHeight.bottom - this.getHeight() - notipos);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(490, 350));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(445, 0, 39, 23);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/e-mail-icon-294x30.gif"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 21, 96, 100);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Vous avez un nouveau message");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(140, 50, 260, 30);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 2, 14));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Vous avez un nv message");
        jLabel3.setName(""); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(150, 130, 280, 30);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 2, 14));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Vous avez un nv message");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(150, 200, 320, 17);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("De:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(100, 130, 40, 30);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Objet du Mail:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(60, 200, 100, 17);

        jButton2.setText("OK, Je lirai plutard");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(10, 300, 130, 23);

        jButton4.setText("Lire Mon Mail");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(150, 300, 100, 23);

        jButton5.setText("Mail Indesirable");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(260, 300, 120, 23);

        jButton6.setText("Repondre");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(390, 300, 90, 23);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/arrnotif.jpg"))); // NOI18N
        getContentPane().add(jLabel7);
        jLabel7.setBounds(0, 0, 490, 350);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    this.dispose();
}//GEN-LAST:event_jButton1ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    try {
        //-------------------------------------------------------------------------------------------
        Enumeration headers = M.getAllHeaders();
        while (headers.hasMoreElements()) {
            Header h = (Header) headers.nextElement();

            if (h.getName().equals("Message-ID")) {
                Enregitrer.Ser_MailLu(h.getValue());

            }
        }
//        System.out.println();
    } catch (MessagingException ex) {
        Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
    }
    //-------------------------------------------------------------------------------------------  
    this.dispose();
}//GEN-LAST:event_jButton2ActionPerformed

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    ReadingMessage r = new ReadingMessage();
    r.MM = M;
    Address[] a;
    try {
        if ((a = M.getFrom()) != null) {
            for (int j = 0; j < a.length; j++) {


                r.Sujet1.setText(M.getSubject());
                r.DE1.setText(a[j].toString());


            }
        }

        if ((a = M.getRecipients(Message.RecipientType.TO)) != null) {
            for (int j = 0; j < a.length; j++) {
//                System.out.println(j);
//                System.out.print(a[j].toString());
                r.A1.setText(a[j].toString());

            }
        }

    } catch (MessagingException ex) {
        Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
    }
    Multipart mp;
    try {
        mp = (Multipart) M.getContent();
        Object p = mp.getBodyPart(0).getContent();
        String q = p.toString();//object has the body content  
        //   System.out.println(q);//prints the body  
        r.jTextArea1.setText(q);

    } catch (IOException ex) {
        Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
    } catch (MessagingException ex) {
        Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
    }
    r.setVisible(true);

    try {
        //----------------------------------pour ne pas lire une autre fois le meme mail-----------------------------------------------------
        Enumeration headers = M.getAllHeaders();
        while (headers.hasMoreElements()) {
            Header h = (Header) headers.nextElement();

            if (h.getName().equals("Message-ID")) {
                Enregitrer.Ser_MailLu(h.getValue());

            }
        }
//        System.out.println();
    } catch (MessagingException ex) {
        Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
    }
    this.dispose();
    //-------------------------------------------------------------------------------------------     
}//GEN-LAST:event_jButton4ActionPerformed

private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    New_Message Nm = new New_Message();
    Nm.obj.setText("RE:" + jLabel4.getText());
    Nm.dest.setText(jLabel3.getText());
    Nm.setVisible(true);
    this.dispose();

    try {
        //----------------------------------pour ne pas lire une autre fois le meme mail-----------------------------------------------------
        Enumeration headers = M.getAllHeaders();
        while (headers.hasMoreElements()) {
            Header h = (Header) headers.nextElement();

            if (h.getName().equals("Message-ID")) {
                Enregitrer.Ser_MailLu(h.getValue());

            }
        }
//        System.out.println();
    } catch (MessagingException ex) {
        Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
    }
    //-------------------------------------------------------------------------------------------     

    this.dispose();
}//GEN-LAST:event_jButton6ActionPerformed

private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    if (javax.swing.JOptionPane.showConfirmDialog(this, "Cet Email sera indésirable!") == 0) {
        Address[] a;
        try {
            if ((a = M.getFrom()) != null) {
                for (int j = 0; j < a.length; j++) {
                    Enregitrer.Ser_MailInd(a[j].toString());
                }
            }
        } catch (MessagingException ex) {
            Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.dispose();
    }
}//GEN-LAST:event_jButton5ActionPerformed

private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    int image_index = 0;
    image_index = Enregitrer.Dser_background_image();
    String background_Image = "arrnotif";
    switch (image_index) {
        case 1:
            background_Image = "arrnotif.jpg";
            break;
        case 2:
            background_Image = "arrnotif2.jpg";
            break;
        case 3:
            background_Image = "arrnotif3.jpg";
            break;
        case 4:
            background_Image = "arrnotif4.jpg";
            break;
        case 5:
            background_Image = "arrnotif5.jpg";
            break;
        case 6:
            background_Image = "arrnotif6.jpg";
            break;
        case 7:
            background_Image = "arrnotif7.jpg";
            break;

        default:
            background_Image = "arrnotif.jpg";
    }
    jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/" + background_Image)));
}//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Notification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Notification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Notification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Notification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                int a = 0;
                new Notification(a).setVisible(true);


            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
