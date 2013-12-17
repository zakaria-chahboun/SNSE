/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snse;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ZAKARIA
 */
public class Evenement {

    private static ArrayList<Evenement_obj> array = new ArrayList<Evenement_obj>();

    public static boolean Add_event(Evenement_obj ev) {
        array.add(ev);
        Enregitrer.Ser_Event();
        return true;

    }

    public static boolean Delete_event(Evenement_obj ev) {
        if (array.contains(ev)) {
            array.remove(ev);
            Enregitrer.Ser_Event();
        } else {
            return false;
        }
        return true;
    }

    public static boolean Update_event(Evenement_obj ev_old, Evenement_obj ev_new) {
        if (Delete_event(ev_old) && Add_event(ev_new)) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<Evenement_obj> all_event() {
        return array;
    }

    public static ArrayList<Evenement_obj> all_event_at(Date d) {
        ArrayList<Evenement_obj> st = new ArrayList<Evenement_obj>();
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).Debut.getDate() == d.getDate()) {
                st.add(array.get(i));
            }
        }

        return st;
    }

    public static ArrayList<Evenement_obj> all_event_afterWeek() {
        Date d = new Date();

        long a = (d.getTime() + 597408000);
        Date h = new Date(a);

        ArrayList<Evenement_obj> st = new ArrayList<Evenement_obj>();
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).Debut.before(h) && array.get(i).Debut.after(d)) {
                st.add(array.get(i));
            }
        }

        return st;
    }

    public static void remplire_arry() {
        Evenement.array = Enregitrer.Dser_Event();
    }

    public static ArrayList<Evenement_obj> check_event() {
        Date currenttime = new Date();
        ArrayList<Evenement_obj> ar = Enregitrer.Dser_Event();
        ArrayList<Evenement_obj> ev = new ArrayList<Evenement_obj>();
        for (int i = 0; i < ar.size(); i++) {

            Date d = ar.get(i).Debut;
            System.out.println("h :" + d.getHours() + "|" + currenttime.getHours() + " Min: " + d.getMinutes()
                    + "|" + currenttime.getMinutes() + " date:" + d.getDate() + "|" + currenttime.getDate()
                    + " Moi :" + d.getMonth() + "|" + currenttime.getMonth() + " An: " + d.getYear() + "|" + currenttime.getYear());
            System.err.println(d);
            System.out.println("----------------------------------------------");
            boolean b1 = (currenttime.getYear() == d.getYear());
            boolean b2 = (currenttime.getMonth() == d.getMonth());
            boolean b3 = (currenttime.getDate() == d.getDate());
            boolean b4 = (currenttime.getHours() == d.getHours());
            boolean b5 = (currenttime.getMinutes() == d.getMinutes());

            if (b1 && b2 && b3 && b4 && b5) {
                ev.add(array.get(i));

            } else if (!currenttime.before(d) && currenttime.before(ar.get(i).Fin)) {
                ev.add(array.get(i));
            }
        }
        return ev;

    }

    public static void check_event_auto() {
//        System.out.println("cc");
        ArrayList<Evenement_obj> ar1 = new ArrayList<Evenement_obj>();
        ar1 = check_event();
        int notipo = ar1.size() * 100;

//        System.out.print(ar1.size());
        for (int i = 0; i < ar1.size(); i++) {
            notipo = notipo - 100;
            Evenement_obj evenement_obj = ar1.get(i);
            final Notification_event n = new Notification_event(notipo);
            n.Debut.setText(evenement_obj.Debut.toString());
            n.Fin.setText(evenement_obj.Fin.toString());
            n.Nom_event.setText(evenement_obj.event_name);
            n.m = evenement_obj;
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
   }
    public static void main(String arg[]) {
//        Date maDate = new Date();
//        System.out.println(maDate.toString());
//Evenement.remplire_arry();
//check_event_auto();
    }
}
