/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.clan;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Clan;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOAddClan extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Clan)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Clan!");
        }

        Clan c = (Clan) ado;

        ArrayList<Clan> clanovi = (ArrayList<Clan>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Clan clan : clanovi) {
            if (clan.getEmail().equals(c.getEmail())) {
                throw new Exception("Clan sa tim emailom vec postoji!");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
