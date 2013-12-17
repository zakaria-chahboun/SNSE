package snse;

import java.io.*;
import java.util.*;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author user
 */
public class Enregitrer {

    public static void serialisation(String login, String password) {
        //_______________________________SERIALISATION DES Mps__________________________________________________________________________________________
        try {
            String ligne = login + ";" + password;
            BufferedWriter fichier2 = new BufferedWriter(new FileWriter("VINCI2"));
            fichier2.write(ligne);
            fichier2.newLine();


            fichier2.close();
        } catch (Exception ex) {
        }
    }

    public static ArrayList<String> Deserialisation() {
        //__________________________________________________Deserialisation mps _______________________________________________________________________
        ArrayList<String> array = new ArrayList<String>();
        try {
            BufferedReader fichier = new BufferedReader(new FileReader("VINCI2"));
            String ligne = null;
            String login, password;


            while ((ligne = fichier.readLine()) != null) {

                login = ligne.substring(0, ligne.indexOf(';'));
                password = ligne.substring(ligne.indexOf(';') + 1);
                array.add(login);
                array.add(password);
            }
            fichier.close();


        } catch (Exception ex) {
        }
        return array;
    }

    public static void Ser_MailLu(String ID_Mail) {
        //_______________________________SERIALISATION DES Mail lu__________________________________________________________________________________________
        ArrayList<String> lu = Dser_MailLu();

        lu.add(ID_Mail);
        try {
            BufferedWriter fichier2 = new BufferedWriter(new FileWriter("Ser_MailLu"));
            for (int i = 0; i < lu.size(); i++) {
                fichier2.write(lu.get(i));
                fichier2.newLine();
            }
            fichier2.close();
        } catch (Exception ex) {
        }
    }

    public static ArrayList<String> Dser_MailLu() {
        //__________________________________________________Deserialisation  Mail lu _______________________________________________________________________
        ArrayList<String> array = new ArrayList<String>();
        try {
            BufferedReader fichier = new BufferedReader(new FileReader("Ser_MailLu"));
            String ligne = null;



            while ((ligne = fichier.readLine()) != null) {



                array.add(ligne);

            }
            fichier.close();


        } catch (Exception ex) {
        }
        return array;
    }

    public static void Ser_MailInd(String EMail) {
        //_______________________________SERIALISATION DES Mails Indesirable__________________________________________________________________________________________
        ArrayList<String> lu = Dser_MailInd();

        lu.add(EMail);
        try {
            BufferedWriter fichier2 = new BufferedWriter(new FileWriter("Ser_MailInd"));
            for (int i = 0; i < lu.size(); i++) {
                fichier2.write(lu.get(i));
                fichier2.newLine();
            }
            fichier2.close();
        } catch (Exception ex) {
        }
    }

    public static ArrayList<String> Dser_MailInd() {
        //__________________________________________________Deserialisation des Mails Indesirable _______________________________________________________________________
        ArrayList<String> array = new ArrayList<String>();
        try {
            BufferedReader fichier = new BufferedReader(new FileReader("Ser_MailInd"));
            String ligne = null;



            while ((ligne = fichier.readLine()) != null) {



                array.add(ligne);

            }
            fichier.close();


        } catch (Exception ex) {
        }
        return array;
    }

    public static void Ser_Event() {
        //_______________________________SERIALISATION DES Evenement__________________________________________________________________________________________
        ArrayList<Evenement_obj> event = Evenement.all_event();


        try {
            BufferedWriter fichier = new BufferedWriter(new FileWriter("Ser_Event"));

            for (int i = 0; i < event.size(); i++) {


                fichier.write(event.get(i).event_name);
                fichier.newLine();
                Long ts = event.get(i).Debut.getTime();
                fichier.write(ts + "");
                fichier.newLine();
                ts = event.get(i).Fin.getTime();
                fichier.write(ts + "");
                fichier.newLine();
                fichier.write(event.get(i).event_description);
                fichier.newLine();
                fichier.write("#************************#@#************************#");
                fichier.newLine();
            }
            fichier.close();
        } catch (Exception ex) {
        }
    }

    public static ArrayList<Evenement_obj> Dser_Event() {
        //__________________________________________________Deserialisation des Evenements_______________________________________________________________________
        ArrayList<Evenement_obj> array = new ArrayList<Evenement_obj>();
        try {
            BufferedReader fichier2 = new BufferedReader(new FileReader("Ser_Event"));
            String ligne = null;


            while ((ligne = fichier2.readLine()) != null) {

                Evenement_obj ev = new Evenement_obj();
                if (!ligne.equals("#************************#@#************************#")) {
                    ev.event_name = ligne;
                }
                ligne = fichier2.readLine();

                long l = Long.parseLong(ligne);
                ev.Debut = new Date(l);
                ligne = fichier2.readLine();

                long l2 = Long.parseLong(ligne);
                ev.Fin = new Date(l2);
                String description = "";
                String li = "";
                while (true) {
                    li = fichier2.readLine();
                    if (li.equals("#************************#@#************************#")) {
                        break;
                    }
                    description = description + li + "\n";
                }
                ev.event_description = description;

                array.add(ev);

            }
            fichier2.close();


        } catch (Exception ex) {
        }

        return array;
    }

    public static void Ser_background_image(int image_index) {
        //_______________________________SERIALISATION DE l'arriere plan__________________________________________________________________________________________
        try {
            BufferedWriter fichier2 = new BufferedWriter(new FileWriter("image_index"));
            fichier2.write(image_index + "");
            fichier2.newLine();


            fichier2.close();
        } catch (Exception ex) {
        }
    }

    public static int Dser_background_image() {
        //__________________________________________________Deserialisation DE l'arriere plan _______________________________________________________________________
        int image_index = 0;
        try {
            BufferedReader fichier = new BufferedReader(new FileReader("image_index"));
            String ligne = null;
            while ((ligne = fichier.readLine()) != null) {
                image_index = Integer.parseInt(ligne);
            }
            fichier.close();


        } catch (Exception ex) {
            return 1;
        }
        return image_index;
    }

    public static boolean suppression_de_donnee() {

        try {


            BufferedWriter fichier1 = new BufferedWriter(new FileWriter("VINCI2"));
            fichier1.write("");
            fichier1.close();

        } catch (Exception ex) {
        }
        return true;
    }

    public static boolean suppression_MailLu() {

        try {


            BufferedWriter fichier1 = new BufferedWriter(new FileWriter("Ser_MailLu"));
            fichier1.write("");
            fichier1.close();

        } catch (Exception ex) {
        }
        return true;
    }
       public static boolean suppression_MailInde() {

        try {


            BufferedWriter fichier1 = new BufferedWriter(new FileWriter("Ser_MailLu"));
            fichier1.write("");
            fichier1.close();

        } catch (Exception ex) {
        }
        return true;
    }

    public static void main(String arg[]) {
//       Date Debut,Fin;
//     String event_name,event_description;
//     Debut=new Date();
//     Fin =new Date();
//     event_name=" FAtimzahra event";
//     event_description="je suis la "
//             + "\n pour toi"
//             + "mais porquoi tu"
//             + "\n n'ais pas d kjd";
//     Evenement_obj ev1=new Evenement_obj(Debut, Fin, event_name, event_description);
//        for (int i = 0; i < 10; i++) {
//           Evenement.Add_event(ev1);     
//        }
//        
//Enregitrer.Ser_Event();
//  
//    Evenement.array.clear();    
//Evenement.array=Enregitrer.Dser_Event();
// System.out.println("size :"+ Evenement.array.size());
//        for (int i = 0; i < Evenement.array.size(); i++) {
//          Evenement_obj ev = Evenement.array.get(i);
//          System.out.println("nom :"+ev.event_name);
//          System.out.println("Debut :"+ev.Debut);
//          System.out.println("Fin :"+ev.Fin);
//          System.out.println("desc :"+ev.event_description);
//          System.out.println("/////////////////////////////////////////");
//          
//            
//        }
//    
    }
}
