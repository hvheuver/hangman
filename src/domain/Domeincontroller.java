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
    private WoordenRepository woordenRepo;
    private String currentWoord;
    private char[] hangmanWoord;
    
    public Domeincontroller() {
        woordenRepo = new WoordenRepository();
    }
    
    public ObservableList<String> getWoorden(){
        return woordenRepo.getWoorden();
    }

    public boolean checkChar(String c) {
        return currentWoord.contains(c);
    }
    
    public void geefVolgendWoord(){
        currentWoord = woordenRepo.geefVolgendWoord();
        hangmanWoord = new char[currentWoord.length()];
        Arrays.fill(hangmanWoord, '_');
    }
    
    public String geefVolledigWoord(){
        return currentWoord;
    }
    
    private String woordNaarHangmanWoord(){
        StringBuilder sb = new StringBuilder();
        for(char c : hangmanWoord){
            sb.append(" ").append(c).append(" ");
        }
        return sb.toString().toUpperCase();
    }
    
    public String geefHangmanWoord(String stringchar){
        char schar = stringchar.toCharArray()[0];
        char[] tempcurrent = currentWoord.toCharArray();
        for(int i = 0; i<tempcurrent.length; i++){
            if(tempcurrent[i] == schar){
                hangmanWoord[i] = schar;
            }
        }
        return woordNaarHangmanWoord();
    }
}
