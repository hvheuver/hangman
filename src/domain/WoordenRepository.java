/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author UGent
 */
public class WoordenRepository {
    private List<String> woorden;
    private List<String> gebruikteWoorden;
    private final Random randomGenerator;
    
    public WoordenRepository() {
        generateStandaardList();
        randomGenerator = new Random();
        gebruikteWoorden = new ArrayList<>();
    }

    private void generateStandaardList() {
        woorden = new ArrayList<>(
            Arrays.asList(new String[]{
                "voiture",
                "armoire",
                "difficile",
                "toujours",
                "ligne",
                "remercier"
            })
        );
    }
    
    public ObservableList<String> getWoorden(){
        return FXCollections.observableArrayList(woorden);
    }
    
    public String geefVolgendWoord(){
        if(woorden.isEmpty()){
            throw new ArrayIndexOutOfBoundsException("Woordenlijst is leeg.");
        }
        int index = randomGenerator.nextInt(woorden.size());
        String gekozen =  woorden.remove(index);
        gebruikteWoorden.add(gekozen);
        return gekozen;
    }
    
    public void resetLijsten(){
        woorden.addAll(gebruikteWoorden);
    }
    
    public void voegWoordToe(String s){
        woorden.add(s);
    }
    
    public void verwijderWoord(String s){
        woorden.remove(s);
    }
}
