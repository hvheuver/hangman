/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Domeincontroller;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author UGent
 */
public class LandingPaneController extends GridPane {
    private Domeincontroller domeincontroller;
    
    public LandingPaneController(Domeincontroller domeincontroller) {
        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("LandingPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try
        {
            loader.load();
            this.domeincontroller = domeincontroller;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void voegWoordenToe(ActionEvent event) {
        Stage s = (Stage) this.getScene().getWindow();
        s.setScene(new Scene(new WoordenToevoegenPanelController(domeincontroller)));
    }

    @FXML
    private void startHangman(ActionEvent event) {
        Stage s = (Stage) this.getScene().getWindow();
        s.setScene(new Scene(new HangmanPanelController(domeincontroller)));
    }
}
