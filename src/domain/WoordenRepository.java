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
                new Woord("adroit", "qui se conduit, qui opère avec habileté, subtilité", "behendig"),
                new Woord("armoire", "meuble de rangement à étagères fermé par une ou des portes", "kast"),
                new Woord("difficile", "compliqué, ne pouvant être compris facilement", "moeilijk"),
                new Woord("toujours", "tout le temps, sans cesse", "altijd"),
                new Woord("ligne", "figure matérialisée par un fil fin", "lijn"),
                new Woord("remercier", "exprimer sa gratitude, dire merci", "bedanken")
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
