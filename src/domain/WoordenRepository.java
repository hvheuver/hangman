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
    private List<Woord> woorden;
    private List<Woord> gebruikteWoorden;
    private final Random randomGenerator;
    
    public WoordenRepository() {
        generateStandaardList();
        randomGenerator = new Random();
        gebruikteWoorden = new ArrayList<>();
    }

    private void generateStandaardList() {
        woorden = new ArrayList<>(
            Arrays.asList(new Woord[]{
                new Woord("voiture", "brumbrum", "auto"),
                new Woord("armoire", "definitie", "vertaling"),
                new Woord("difficile", "definitie", "vertaling"),
                new Woord("toujours", "definitie", "vertaling"),
                new Woord("ligne", "definitie", "vertaling"),
                new Woord("remercier", "definitie", "vertaling")
            })
        );
    }
    
    public ObservableList<Woord> getWoorden(){
        return FXCollections.observableArrayList(woorden);
    }
    
    public Woord geefVolgendWoord(){
        if(woorden.isEmpty()){
            throw new ArrayIndexOutOfBoundsException("Woordenlijst is leeg.");
        }
        int index = randomGenerator.nextInt(woorden.size());
        Woord gekozen =  woorden.remove(index);
        gebruikteWoorden.add(gekozen);
        return gekozen;
    }
    
    public void resetLijsten(){
        woorden.addAll(gebruikteWoorden);
        gebruikteWoorden = new ArrayList<>();
    }
    
    public void voegWoordToe(String woord, String definitie, String vertaling){
        woorden.add(new Woord(woord, definitie, vertaling));
    }
    
    public void verwijderWoord(Woord w){
        woorden.remove(w);
    }
}
