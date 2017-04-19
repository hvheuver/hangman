/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author UGent
 */
public class WoordenRepository {
    private List<String> woorden;

    public WoordenRepository() {
        generateStandaardList();
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
}
