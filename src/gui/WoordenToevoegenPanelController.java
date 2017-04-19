/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Domeincontroller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author UGent
 */
public class WoordenToevoegenPanelController extends GridPane{
    private Domeincontroller domeincontroller;
    @FXML
    private ListView<String> listViewWoorden;
    @FXML
    private TextField woordTextfield;
    
    public WoordenToevoegenPanelController(Domeincontroller domeincontroller) {
         FXMLLoader loader
                = new FXMLLoader(getClass().getResource("WoordenToevoegenPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try
        {
            loader.load();
            this.domeincontroller = domeincontroller;
            listViewWoorden.setItems(domeincontroller.getWoorden());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void voegWoordToe(ActionEvent event) {
    }

    @FXML
    private void terugNaarLanding(ActionEvent event) {
        Stage s = (Stage) this.getScene().getWindow();
        s.setScene(new Scene(new LandingPaneController(domeincontroller)));
    }
}
