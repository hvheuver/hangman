/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javafx.collections.ObservableList;

/**
 *
 * @author UGent
 */
public class Domeincontroller {
    private WoordenRepository woordenRepo;

    public Domeincontroller() {
        woordenRepo = new WoordenRepository();
    }
    
    public ObservableList<String> getWoorden(){
        return woordenRepo.getWoorden();
    }
}
