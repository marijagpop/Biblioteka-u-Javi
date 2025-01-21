/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Korisnik
 */
public class StavkaRezervacije extends AbstractDomainObject {

    private Rezervacija rezervacija;
    private int rb;
    private double cena;
    private Knjiga knjiga;

    public StavkaRezervacije(Rezervacija rezervacija, int rb, double cena, Knjiga knjiga) {
        this.rezervacija = rezervacija;
        this.rb = rb;
        this.cena = cena;
        this.knjiga = knjiga;
    }

    public StavkaRezervacije() {
    }

    @Override
    public String nazivTabele() {
        return " StavkaRezervacije ";
    }

    @Override
    public String alijas() {
        return " sr ";
    }

    @Override
    public String join() {
        return " JOIN KNJIGA K ON (K.KNJIGAID = SR.KNJIGAID) "
                + "JOIN ZANR Z ON (Z.ZANRID = K.ZANRID) "
                + "JOIN REZERVACIJA R ON (R.REZERVACIJAID = SR.REZERVACIJAID) "
                + "JOIN CLAN C ON (C.CLANID = R.CLANID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = R.ADMINISTRATORID)";
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
                    rs.getDate("datumOd"), rs.getDate("datumDo"), rs.getDouble("r.cena"),
                    rs.getDouble("popust"), rs.getDouble("cenaSaPopustom"), c, a, null);
            
            Zanr z = new Zanr(rs.getLong("ZanrID"), rs.getString("naziv"));

            Knjiga k = new Knjiga(rs.getLong("knjigaID"), rs.getString("naziv"),
                    rs.getString("autor"), rs.getString("opis"), rs.getDouble("cenaPoDanu"), z);
            
            StavkaRezervacije sr = new StavkaRezervacije(r, rs.getInt("rb"), 
                    rs.getDouble("sr.cena"), k);

            lista.add(sr);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (rezervacijaID, rb, cena, knjigaID) ";
    }

    @Override
    public String uslov() {
        return " rezervacijaID = " + rezervacija.getRezervacijaID();
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + rezervacija.getRezervacijaID() + ", " + rb + ", "
                + " " + cena + ", '" + knjiga.getKnjigaID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        return " WHERE R.REZERVACIJAID = " + rezervacija.getRezervacijaID();
    }

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

}
