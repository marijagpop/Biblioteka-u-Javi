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
public class Clan extends AbstractDomainObject {
    
    private Long clanID;
    private String ime;
    private String prezime;
    private String email;
    private String nivo;

    @Override
    public String toString() {
        return ime + " " + prezime + " (Nivo: " + nivo + ")";
    }

    public Clan(Long clanID, String ime, String prezime, String email, String nivo) {
        this.clanID = clanID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.nivo = nivo;
    }

    public Clan() {
    }
    
    @Override
    public String nazivTabele() {
        return " Clan ";
    }

    @Override
    public String alijas() {
        return " c ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Clan c = new Clan(rs.getLong("ClanID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("email"), rs.getString("nivo"));

            lista.add(c);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, email, nivo) ";
    }

    @Override
    public String uslov() {
        return " ClanID = " + clanID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + email + "', '" + nivo + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " email = '" + email + "', nivo = '" + nivo + "' ";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getClanID() {
        return clanID;
    }

    public void setClanID(Long clanID) {
        this.clanID = clanID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNivo() {
        return nivo;
    }

    public void setNivo(String nivo) {
        this.nivo = nivo;
    }
    
}
