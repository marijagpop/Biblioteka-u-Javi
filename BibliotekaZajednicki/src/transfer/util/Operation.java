/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author PC
 */
public interface Operation {

    public static final int LOGIN = 0;

    public static final int ADD_CLAN = 1;
    public static final int DELETE_CLAN = 2;
    public static final int UPDATE_CLAN = 3;
    public static final int GET_ALL_CLAN = 4;

    public static final int ADD_REZERVACIJA = 5;
    public static final int DELETE_REZERVACIJA = 6;
    public static final int UPDATE_REZERVACIJA = 7;
    public static final int GET_ALL_REZERVACIJA = 8;

    public static final int GET_ALL_STAVKA_REZERVACIJE = 9;

    public static final int GET_ALL_KNJIGA = 10;
    
    public static final int GET_ALL_ZANR = 11;

}
