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
 * @author stefa
 */
public class Komponenta extends AbstractDomainObject{

    private int komponentaId;
    private String naziv;
    private String opis;

    public Komponenta() {
    }

    public Komponenta(int komponentaId, String naziv, String opis) {
        this.komponentaId = komponentaId;
        this.naziv = naziv;
        this.opis = opis;
    }
    
    
    
    @Override
    public String nazivTabele() {

        return "Komponenta";
    }

    @Override
    public String alijas() {

        return "komp";
    }

    @Override
    public String join() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        
        ArrayList<AbstractDomainObject> lista= new ArrayList<>();
        
        while(rs.next()){
            Komponenta kom= new Komponenta(rs.getInt("KomponentaId"), rs.getString("Naziv"), rs.getString("Opis"));
            
            lista.add(kom);
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

        return "komponentaId= " +komponentaId;
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

    public int getKomponentaId() {
        return komponentaId;
    }

    public void setKomponentaId(int komponentaId) {
        this.komponentaId = komponentaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
    
}
