/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Arrays;
import javafx.collections.ObservableList;

/**
 *
 * @author UGent
 */
public class Domeincontroller {

    private final WoordenRepository woordenRepo;
    private Woord currentWoordObject;
    private String currentWoord;
    private char[] hangmanWoord;
    private int beurten;
    private final int MAX_BEURTEN;
    
    //geen echte domeincontroller but whatever.
    public Domeincontroller() {
        woordenRepo = new WoordenRepository();
        beurten = 0;
        MAX_BEURTEN = 7; //aanpassen aan aantal images
    }

    public ObservableList<Woord> getWoorden() {
        return woordenRepo.getWoorden();
    }

    public boolean checkChar(String c) {
        boolean komtVoor = currentWoord.contains(c);
        if(!komtVoor){
            beurten++;
        }
        return komtVoor;
    }

    public void geefVolgendWoord() {
        currentWoordObject = woordenRepo.geefVolgendWoord();
        currentWoord = currentWoordObject.getWoord().toUpperCase();
        hangmanWoord = new char[currentWoord.length()];
        Arrays.fill(hangmanWoord, '_');
        beurten = 0;
    }

    public String geefVolledigWoord() {
        return currentWoord;
    }

    private String woordNaarHangmanWoord() {
        StringBuilder sb = new StringBuilder();
        for (char c : hangmanWoord) {
            sb.append(" ").append(c).append(" ");
        }
        return sb.toString().toUpperCase();
    }

    public String geefHangmanWoord() {
        return woordNaarHangmanWoord();
    }

    public String geefHangmanWoord(String stringchar) {
        char schar = stringchar.toCharArray()[0];
        char[] tempcurrent = currentWoord.toCharArray();
        for (int i = 0; i < tempcurrent.length; i++) {
            if (tempcurrent[i] == schar) {
                hangmanWoord[i] = schar;
            }
        }
        return woordNaarHangmanWoord();
    }

    public int checkWinOfVerlies() {
        //geen van beide = 0        
        //win = 1
        //verlies = 2
        if(beurten == MAX_BEURTEN){
            return 2;
        } else {
            String s = new String(hangmanWoord);
            if(!s.contains("_")){
                return 1;
            } else{
                return 0;
            }
        }
    }
    
    public void voegWoordToe(String woord, String definitie, String vertaling){
        woordenRepo.voegWoordToe(woord, definitie, vertaling);
    }
}
