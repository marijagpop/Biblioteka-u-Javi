/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Korisnik
 */
public class Rezervacija extends AbstractDomainObject {

    private Long rezervacijaID;
    private Date datumOd;
    private Date datumDo;
    private double cena;
    private double popust;
    private double cenaSaPopustom;
    private Clan clan;
    private Administrator administrator;
    private ArrayList<StavkaRezervacije> stavkeRezervacije;

    public Rezervacija(Long rezervacijaID, Date datumOd, Date datumDo, double cena, double popust, double cenaSaPopustom, Clan clan, Administrator administrator, ArrayList<StavkaRezervacije> stavkeRezervacije) {
        this.rezervacijaID = rezervacijaID;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.cena = cena;
        this.popust = popust;
        this.cenaSaPopustom = cenaSaPopustom;
        this.clan = clan;
        this.administrator = administrator;
        this.stavkeRezervacije = stavkeRezervacije;
    }

    public Rezervacija() {
    }

    @Override
    public String nazivTabele() {
        return " Rezervacija ";
    }

    @Override
    public String alijas() {
        return " r ";
    }

    @Override
    public String join() {
        return " JOIN CLAN C ON (C.CLANID = R.CLANID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = R.ADMINISTRATORID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("a.Ime"), rs.getString("a.Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            Clan c = new Clan(rs.getLong("ClanID"),
                    rs.getString("c.Ime"), rs.getString("c.Prezime"),
                    rs.getString("email"), rs.getString("nivo"));

            Rezervacija r = new Rezervacija(rs.getLong("rezervacijaID"),
                    rs.getDate("datumOd"), rs.getDate("datumDo"), rs.getDouble("cena"),
                    rs.getDouble("popust"), rs.getDouble("cenaSaPopustom"), c, a, null);

            lista.add(r);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (datumOd, datumDo, cena, popust, cenaSaPopustom, clanID, administratorID) ";
    }

    @Override
    public String uslov() {
        return " rezervacijaID = " + rezervacijaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new java.sql.Date(datumOd.getTime()) + "', "
                + "'" + new java.sql.Date(datumDo.getTime()) + "', "
                + " " + cena + ", " + popust + ", " + cenaSaPopustom + ", "
                + clan.getClanID() + ", " + administrator.getAdministratorID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " datumOd = '" + new java.sql.Date(datumOd.getTime()) + "', "
                + "datumDo = '" + new java.sql.Date(datumDo.getTime()) + "', "
                + "cena = " + cena + ", cenaSaPopustom = " + cenaSaPopustom + " ";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getRezervacijaID() {
        return rezervacijaID;
    }

    public void setRezervacijaID(Long rezervacijaID) {
        this.rezervacijaID = rezervacijaID;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    public double getCenaSaPopustom() {
        return cenaSaPopustom;
    }

    public void setCenaSaPopustom(double cenaSaPopustom) {
        this.cenaSaPopustom = cenaSaPopustom;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public ArrayList<StavkaRezervacije> getStavkeRezervacije() {
        return stavkeRezervacije;
    }

    public void setStavkeRezervacije(ArrayList<StavkaRezervacije> stavkeRezervacije) {
        this.stavkeRezervacije = stavkeRezervacije;
    }

}
