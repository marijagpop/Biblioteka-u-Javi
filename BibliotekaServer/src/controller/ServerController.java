/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Administrator;
import domain.Clan;
import domain.Knjiga;
import domain.Rezervacija;
import domain.StavkaRezervacije;
import domain.Zanr;
import java.util.ArrayList;
import so.clan.SOAddClan;
import so.clan.SODeleteClan;
import so.clan.SOGetAllClan;
import so.clan.SOUpdateClan;
import so.knjiga.SOGetAllKnjiga;
import so.login.SOLogin;
import so.rezervacija.SOAddRezervacija;
import so.rezervacija.SODeleteRezervacija;
import so.rezervacija.SOGetAllRezervacija;
import so.rezervacija.SOUpdateRezervacija;
import so.stavka_rezervacije.SOGetAllStavkaRezervacije;
import so.zanr.SOGetAllZanr;

/**
 *
 * @author PC
 */
public class ServerController {

    private static ServerController instance;

    private ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(administrator);
        return so.getUlogovani();
    }

    public void addClan(Clan clan) throws Exception {
        (new SOAddClan()).templateExecute(clan);
    }

    public void addRezervacija(Rezervacija rezervacija) throws Exception {
        (new SOAddRezervacija()).templateExecute(rezervacija);
    }

    public void deleteClan(Clan clan) throws Exception {
        (new SODeleteClan()).templateExecute(clan);
    }

    public void deleteRezervacija(Rezervacija rezervacija) throws Exception {
        (new SODeleteRezervacija()).templateExecute(rezervacija);
    }

    public void updateClan(Clan clan) throws Exception {
        (new SOUpdateClan()).templateExecute(clan);
    }

    public void updateRezervacija(Rezervacija rezervacija) throws Exception {
        (new SOUpdateRezervacija()).templateExecute(rezervacija);
    }

    public ArrayList<Clan> getAllClan() throws Exception {
        SOGetAllClan so = new SOGetAllClan();
        so.templateExecute(new Clan());
        return so.getLista();
    }

    public ArrayList<Rezervacija> getAllRezervacija() throws Exception {
        SOGetAllRezervacija so = new SOGetAllRezervacija();
        so.templateExecute(new Rezervacija());
        return so.getLista();
    }

    public ArrayList<Knjiga> getAllKnjiga() throws Exception {
        SOGetAllKnjiga so = new SOGetAllKnjiga();
        so.templateExecute(new Knjiga());
        return so.getLista();
    }

    public ArrayList<Zanr> getAllZanr() throws Exception {
        SOGetAllZanr so = new SOGetAllZanr();
        so.templateExecute(new Zanr());
        return so.getLista();
    }

    public ArrayList<StavkaRezervacije> getAllStavkaRezervacije() throws Exception {
        SOGetAllStavkaRezervacije so = new SOGetAllStavkaRezervacije();
        so.templateExecute(new StavkaRezervacije());
        return so.getLista();
    }

}
