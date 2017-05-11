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
                new Woord("remercier", "exprimer sa gratitude, dire merci", "bedanken"),
                new Woord("cybermonde", "L’univers virtuel d’Internet et des réseaux informatiques.", "cyberspace"),
                new Woord("interface", "Élément formant la liaison entre deux systèmes on deux entités.", "verbinding"),    
                new Woord("formatage", "Opération permettant de préparer un disque à l’emploi.", "formatteren"),
                new Woord("serveur", "Ordinateur fonctionnant au service d’un réseau, outil d’un service organisant la diffusion.", "server"),
                new Woord("bloquer", "Ils cessent de marcher.", "blokkeren"),
                new Woord("shareware", "Logiciel pouvant être utilisé gratuitement pendant une certaine période et que l’on doit ensuite acheter.", "proefversie"),
                new Woord("clone", "Offre les mêmes fonctionnalités que l’original dont il s’inspire.", "kloon"),
                new Woord("fichier", "Un ensemble d’informations enregistrées sur un disque contenant des données ou un programme.", "bestand"),
                new Woord("identificateur", "Nom utilisé pour se connecter à l’Internet et récupérer son courrier électronique.", "login"),
                new Woord("signet", "Une sorte de marque-page automatique qui permet de retrouver un site Internet déjà visité.", "bookmark"),
                new Woord("pirate", "Pirate qui s’introduit illégalement dans les systèmes informatiques.", "hacker"),
                new Woord("questionnaire", "Une liste de questions.", "vragenlijst"),
                new Woord("souris", "Objet qui sert à se déplacer sur l’écran d’un ordinateur.", "muis")
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
