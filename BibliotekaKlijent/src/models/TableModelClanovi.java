/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Clan;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TableModelClanovi extends AbstractTableModel implements Runnable {

    private ArrayList<Clan> lista;
    private String[] kolone = {"ID", "Ime", "Prezime", "Nivo", "Email"};
    private String parametar = "";

    public TableModelClanovi() {
        try {
            lista = ClientController.getInstance().getAllClan();
        } catch (Exception ex) {
            Logger.getLogger(TableModelClanovi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Clan c = lista.get(row);

        switch (column) {
            case 0:
                return c.getClanID();
            case 1:
                return c.getIme();
            case 2:
                return c.getPrezime();
            case 3:
                return c.getNivo();
            case 4:
                return c.getEmail();

            default:
                return null;
        }
    }

    public Clan getSelectedClan(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelClanovi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllClan();
            if (!parametar.equals("")) {
                ArrayList<Clan> novaLista = new ArrayList<>();
                for (Clan c : lista) {
                    if (c.getIme().toLowerCase().contains(parametar.toLowerCase())
                            || c.getPrezime().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(c);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
