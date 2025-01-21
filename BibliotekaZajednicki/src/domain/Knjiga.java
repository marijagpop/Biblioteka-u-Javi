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
public class Knjiga extends AbstractDomainObject {

    private Long knjigaID;
    private String naziv;
    private String autor;
    private String opis;
    private double cenaPoDanu;
    private Zanr zanr;

    public Knjiga(Long knjigaID, String naziv, String autor, String opis, double cenaPoDanu, Zanr zanr) {
        this.knjigaID = knjigaID;
        this.naziv = naziv;
        this.autor = autor;
        this.opis = opis;
        this.cenaPoDanu = cenaPoDanu;
        this.zanr = zanr;
    }

    public Knjiga() {
    }

    @Override
    public String nazivTabele() {
        return " Knjiga ";
    }

    @Override
    public String alijas() {
        return " k ";
    }

    @Override
    public String join() {
        return " JOIN ZANR Z ON (Z.ZANRID = K.ZANRID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            Zanr z = new Zanr(rs.getLong("ZanrID"), rs.getString("naziv"));

            Knjiga k = new Knjiga(rs.getLong("knjigaID"), rs.getString("naziv"),
                    rs.getString("autor"), rs.getString("opis"), rs.getDouble("cenaPoDanu"), z);

            lista.add(k);
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
        return " knjigaID = " + knjigaID;
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

    public Long getKnjigaID() {
        return knjigaID;
    }

    public void setKnjigaID(Long knjigaID) {
        this.knjigaID = knjigaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCenaPoDanu() {
        return cenaPoDanu;
    }

    public void setCenaPoDanu(double cenaPoDanu) {
        this.cenaPoDanu = cenaPoDanu;
    }

    public Zanr getZanr() {
        return zanr;
    }

    public void setZanr(Zanr zanr) {
        this.zanr = zanr;
    }

}
