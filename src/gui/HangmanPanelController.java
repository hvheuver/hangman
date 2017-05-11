/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Domeincontroller;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author UGent
 */
public class HangmanPanelController extends GridPane {

    private Domeincontroller domeincontroller;
    private int imgcounter;

    private GridPane buttonContainer;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label woordLabel;
    @FXML
    private Label definitionLabel;
    @FXML
    private ImageView hangmanImageView;
    @FXML
    private Label hangmanLabel;
    @FXML
    private StackPane buttonOuterContainer;
    @FXML
    private Label vertalingLabel;
    
    @FXML
    private TextField vertalingText;
    @FXML
    private Label vertalingBericht;
    @FXML
    private Button translateButton;
    
    int aantalWoorden;
    int doneWoorden;
    @FXML
    private Label compteurLabel;
    
    public HangmanPanelController(Domeincontroller domeincontroller) {
        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("HangmanPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
            this.domeincontroller = domeincontroller;
            aantalWoorden = domeincontroller.getWoorden().size();
            startGame();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void generateButtons() {
        buttonContainer = new GridPane();
        char[] chars = new char[]{
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };
        //26 letters -> 13 buttons per rij
        int row = 0;
        int col = 0;
        for (char c : chars) {
            Button b = new Button("" + c);
            b.setPadding(new Insets(10, 20, 10, 20));
            b.setOnAction((ActionEvent event) -> {
                String eventChar = ((Button) event.getSource()).getText();
                processAction(eventChar);
                ((Button) event.getSource()).setDisable(true);
            });
            b.setStyle("-fx-focus-color: transparent;"
                    + "-fx-faint-focus-color: transparent;");
            buttonContainer.add(b, col, row);
            GridPane.setMargin(b, new Insets(5, 5, 5, 5));
            if (col == 12) {
                col = 0;
                row++;
            } else {
                col++;
            }
        }
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.autosize();
        buttonOuterContainer.getChildren().add(buttonContainer);
    }

    @FXML
    private void toonOplossing(ActionEvent event) {
        hangmanImageView.setImage(new Image("/resources/8.png"));
        endgame(false);
        translateButton.setDisable(true);
    }

    @FXML
    private void gaNaarVolgendWoord(ActionEvent event) {
        imgcounter = 0;
        hangmanImageView.setImage(null);
        doneWoorden++;
        compteurLabel.setText("Compteur: "+doneWoorden+"/"+aantalWoorden);
        startGame();
        translateButton.setDisable(false);
    }

    private void processAction(String stringchar) {
        boolean isvalid = domeincontroller.checkChar(stringchar);
        if (isvalid) {
            hangmanLabel.setText(domeincontroller.geefHangmanWoord(stringchar));
        } else {
            imgcounter++;
            updateimg();
            System.out.println("Wrong! Hangman update");
        }
        switch (domeincontroller.checkWinOfVerlies()) {
            case 1: {
                domeincontroller.addScore(1);
                endgame(true);
                break;
            }
            case 2: {
                endgame(false);
                translateButton.setDisable(true);
                updateVertaling(domeincontroller.geefVertaling());
                break;
            }
        }
    }

    private void startGame() {
        try {
            domeincontroller.geefVolgendWoord();
        } catch (ArrayIndexOutOfBoundsException ex) {
            toonEindscherm();
        }
        //remove and generate
        updateVertaling("");
        updateWoord("");
        updateDefinitie("");
        vertalingBericht.setText("");
        vertalingText.setText("");
        if (!buttonOuterContainer.getChildren().isEmpty()) {
            buttonOuterContainer.getChildren().removeAll(buttonOuterContainer.getChildren());
        }
        generateButtons();
        hangmanLabel.setText(domeincontroller.geefHangmanWoord());
    }

    private void endgame(boolean win) {
        Label label;
        if (win) {
            label = new Label("Tu as gagné!");
        } else {
            label = new Label("Dommage, tu as perdu!");
        }
        updateScore(domeincontroller.getScore());
        updateWoord(domeincontroller.geefVolledigWoord());
        updateDefinitie(domeincontroller.geefDefinitie());
        label.setFont(new Font(30));
        buttonContainer.getChildren().setAll(label);
    }

    private void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    private void updateWoord(String woord) {
        woordLabel.setText("Mot: " + woord);
    }

    private void updateDefinitie(String definitie) {
        definitionLabel.setText("Definition: " + definitie);
    }

    private void updateVertaling(String vertaling) {
        vertalingLabel.setText("Traduction: " + vertaling);
    }

    private void toonEindscherm() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Oei de woordjes zijn op.");
        alert.setContentText("Uw eindscore is " + domeincontroller.getScore() + " van de maximale " + ((aantalWoorden*2)+aantalWoorden));
        alert.showAndWait();
        //back to landing
        domeincontroller.resetGame();
        Stage s = (Stage) this.getScene().getWindow();
        s.setScene(new Scene(new LandingPaneController(domeincontroller)));
    }
    
    
    @FXML
    private void vertaalWoord(ActionEvent event) {
        String ver = vertalingText.getText();
        if (ver.toLowerCase().equals(domeincontroller.geefVertaling().toLowerCase())) {
            domeincontroller.addScore(2);
            updateVertaling(domeincontroller.geefVertaling());
            vertalingBericht.setText("La traduction est correcte!");
            endgame(true);
        } else{
            updateVertaling(domeincontroller.geefVertaling());
            vertalingBericht.setText("La traduction n'est pas correcte!");
        }
        translateButton.setDisable(true);
    }

    private void updateimg() {
        String path = "/resources/"+imgcounter+".png";
        hangmanImageView.setImage(new Image(path));
    }

    @FXML
    private void terugNaarLanding(ActionEvent event) {
        Stage s = (Stage) this.getScene().getWindow();
        s.setScene(new Scene(new LandingPaneController(domeincontroller)));
    }

}
