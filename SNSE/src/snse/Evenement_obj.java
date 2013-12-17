/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snse;

import java.util.Date;

/**
 *
 * @author ZAKARIA
 */
public class Evenement_obj {

    public Date Debut, Fin;
    public String event_name, event_description;

    public Evenement_obj() {
    }

    public Evenement_obj(Date Debut, Date Fin, String event_name, String event_description) {
        this.Debut = Debut;
        this.Fin = Fin;
        this.event_name = event_name;
        this.event_description = event_description;
    }
}
