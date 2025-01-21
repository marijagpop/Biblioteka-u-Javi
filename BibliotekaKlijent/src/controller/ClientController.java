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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author PC
 */
public class ClientController {

    private static ClientController instance;

    private ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        return (Administrator) sendRequest(Operation.LOGIN, administrator);
    }

    public void addClan(Clan clan) throws Exception {
        sendRequest(Operation.ADD_CLAN, clan);
    }

    public void addRezervacija(Rezervacija rezervacija) throws Exception {
        sendRequest(Operation.ADD_REZERVACIJA, rezervacija);
    }

    public void deleteClan(Clan clan) throws Exception {
        sendRequest(Operation.DELETE_CLAN, clan);
    }

    public void deleteRezervacija(Rezervacija rezervacija) throws Exception {
        sendRequest(Operation.DELETE_REZERVACIJA, rezervacija);
    }

    public void updateClan(Clan clan) throws Exception {
        sendRequest(Operation.UPDATE_CLAN, clan);
    }

    public void updateRezervacija(Rezervacija rezervacija) throws Exception {
        sendRequest(Operation.UPDATE_REZERVACIJA, rezervacija);
    }

    public ArrayList<Clan> getAllClan() throws Exception {
        return (ArrayList<Clan>) sendRequest(Operation.GET_ALL_CLAN, null);
    }

    public ArrayList<Rezervacija> getAllRezervacija() throws Exception {
        return (ArrayList<Rezervacija>) sendRequest(Operation.GET_ALL_REZERVACIJA, null);
    }

    public ArrayList<Knjiga> getAllKnjiga() throws Exception {
        return (ArrayList<Knjiga>) sendRequest(Operation.GET_ALL_KNJIGA, null);
    }

    public ArrayList<Zanr> getAllZanr() throws Exception {
        return (ArrayList<Zanr>) sendRequest(Operation.GET_ALL_ZANR, null);
    }

    public ArrayList<StavkaRezervacije> getAllStavkaRezervacije() throws Exception {
        return (ArrayList<StavkaRezervacije>) sendRequest(Operation.GET_ALL_STAVKA_REZERVACIJE, null);
    }

    private Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(operation, data);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) in.readObject();

        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getException();
        } else {
            return response.getData();
        }

    }
}
