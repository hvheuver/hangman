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
                
                new Woord("cybermonde", "L’univers virtuel d’Internet et des réseaux informatiques.", "cyberspace"),
                new Woord("interface", "Élément formant la liaison entre deux systèmes on deux entités.", "verbinding"),    
                new Woord("carte graphique", "Sans la carte graphique, l’écran reste noir.", "grafische kaart"),
                new Woord("disque dur", "Mémoire dans laquelle sont stockés les logiciel les logiciels utilisés et les documents ou fichers crées.", "harde schijf"),
                new Woord("formatage", "Opération permettant de préparer un disque à l’emploi.", "formatteren"),
                new Woord("moteur de recherche", "Serveur équipé de logiciels permettant  de rechercher une information ou un site à partir de mots clés.", "zoekmachine"),
                new Woord("serveur", "Ordinateur fonctionnant au service d’un réseau, outil d’un service organisant la diffusion.", "server"),
                new Woord("se bloquer", "Ils cessent de marcher.", "blokkeren"),
                new Woord("shareware", "Logiciel pouvant être utilisé gratuitement pendant une certaine période et que l’on doit ensuite acheter.", "proefversie"),
                new Woord("clone", "Offre les mêmes fonctionnalités que l’original dont il s’inspire.", "kloon"),
                new Woord("disque compact", "Un disque compact à lecture laser sur lequel on imprime du texte, du son, des images fixes et animées décryptable par ordinateur pourvu d’un lecteur CD.", "cd-rom"),
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
