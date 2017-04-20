/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Domeincontroller;
import domain.Woord;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
    private TextField woordTextfield;
    @FXML
    private TextArea definitieTextArea;
    @FXML
    private TextField vertalingTextField;
    @FXML
    private TableView<Woord> woordenTable;
    @FXML
    private TableColumn<Woord, String> woordCol;
    @FXML
    private TableColumn<Woord, String> definitieCol;
    @FXML
    private TableColumn<Woord, String> vertalingCol;
    
    public WoordenToevoegenPanelController(Domeincontroller domeincontroller) {
         FXMLLoader loader
                = new FXMLLoader(getClass().getResource("WoordenToevoegenPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try
        {
            loader.load();
            this.domeincontroller = domeincontroller;
            setupTable();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void setupTable(){
        woordCol.setCellValueFactory(w -> new ReadOnlyStringWrapper(w.getValue().getWoord()));
        woordCol.setCellFactory(w -> new WrappingTextFieldTableCell<>());
        definitieCol.setCellValueFactory(w -> new ReadOnlyStringWrapper(w.getValue().getDefinitie()));
        definitieCol.setCellFactory(w -> new WrappingTextFieldTableCell<>());
        vertalingCol.setCellValueFactory(w -> new ReadOnlyStringWrapper(w.getValue().getVertaling()));
        vertalingCol.setCellFactory(w -> new WrappingTextFieldTableCell<>());
        woordenTable.getItems().addAll(domeincontroller.getWoorden());
    }
    
    @FXML
    private void voegWoordToe(ActionEvent event) {
        //geen validatie
        domeincontroller.voegWoordToe(woordTextfield.getText(), definitieTextArea.getText(), vertalingTextField.getText());
        woordenTable.getItems().clear();
        woordenTable.getItems().addAll(domeincontroller.getWoorden());
    }

    @FXML
    private void terugNaarLanding(ActionEvent event) {
        Stage s = (Stage) this.getScene().getWindow();
        s.setScene(new Scene(new LandingPaneController(domeincontroller)));
    }
}
