/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Administrator;
import domain.Clan;
import domain.Rezervacija;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author PC
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                Response response = handleRequest(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {
                case Operation.ADD_CLAN:
                    ServerController.getInstance().addClan((Clan) request.getData());
                    break;
                case Operation.ADD_REZERVACIJA:
                    ServerController.getInstance().addRezervacija((Rezervacija) request.getData());
                    break;
                case Operation.DELETE_CLAN:
                    ServerController.getInstance().deleteClan((Clan) request.getData());
                    break;
                case Operation.DELETE_REZERVACIJA:
                    ServerController.getInstance().deleteRezervacija((Rezervacija) request.getData());
                    break;
                case Operation.UPDATE_CLAN:
                    ServerController.getInstance().updateClan((Clan) request.getData());
                    break;
                case Operation.UPDATE_REZERVACIJA:
                    ServerController.getInstance().updateRezervacija((Rezervacija) request.getData());
                    break;
                case Operation.GET_ALL_CLAN:
                    response.setData(ServerController.getInstance().getAllClan());
                    break;
                case Operation.GET_ALL_KNJIGA:
                    response.setData(ServerController.getInstance().getAllKnjiga());
                    break;
                case Operation.GET_ALL_REZERVACIJA:
                    response.setData(ServerController.getInstance().getAllRezervacija());
                    break;
                case Operation.GET_ALL_STAVKA_REZERVACIJE:
                    response.setData(ServerController.getInstance().getAllStavkaRezervacije());
                    break;
                case Operation.GET_ALL_ZANR:
                    response.setData(ServerController.getInstance().getAllZanr());
                    break;
                case Operation.LOGIN:
                    Administrator administrator = (Administrator) request.getData();
                    Administrator ulogovani = ServerController.getInstance().login(administrator);
                    response.setData(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception ex) {
            response.setResponseStatus(ResponseStatus.Error);
            response.setException(ex);
        }
        return response;
    }

}
