/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.rezervacija;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Rezervacija;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOUpdateRezervacija extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Rezervacija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Rezervacija!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }

}
