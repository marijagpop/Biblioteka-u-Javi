/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.stavka_rezervacije;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.StavkaRezervacije;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetAllStavkaRezervacije extends AbstractSO {

    private ArrayList<StavkaRezervacije> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof StavkaRezervacije)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StavkaRezervacije!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> stavkeRezervacije = DBBroker.getInstance().select(ado);
        lista = (ArrayList<StavkaRezervacije>) (ArrayList<?>) stavkeRezervacije;
    }

    public ArrayList<StavkaRezervacije> getLista() {
        return lista;
    }

}
