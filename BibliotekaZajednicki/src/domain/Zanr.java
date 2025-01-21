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
public class Zanr extends AbstractDomainObject {

    private Long zanrID;
    private String naziv;

    @Override
    public String toString() {
        return naziv;
    }

    public Zanr(Long zanrID, String naziv) {
        this.zanrID = zanrID;
        this.naziv = naziv;
    }

    public Zanr() {
    }

    @Override
    public String nazivTabele() {
        return " Zanr ";
    }

    @Override
    public String alijas() {
        return " z ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Zanr z = new Zanr(rs.getLong("ZanrID"), rs.getString("naziv"));

            lista.add(z);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String uslov() {
        return " ZanrID = " + zanrID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getZanrID() {
        return zanrID;
    }

    public void setZanrID(Long zanrID) {
        this.zanrID = zanrID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

}
