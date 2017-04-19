/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Domeincontroller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author UGent
 */
public class HangmanPanelController extends GridPane {
    private Domeincontroller domeincontroller;
    
    @FXML
    private FlowPane buttonContainer;

    public HangmanPanelController(Domeincontroller domeincontroller) {
         FXMLLoader loader
                = new FXMLLoader(getClass().getResource("HangmanPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try
        {
            loader.load();
            this.domeincontroller = domeincontroller;
        } catch (IOException ex) {
            Logger.getLogger(LandingPaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @FXML
    private void toonOplossing(ActionEvent event) {
    }

    @FXML
    private void gaNaarVolgendWoord(ActionEvent event) {
    }
    
    
}
