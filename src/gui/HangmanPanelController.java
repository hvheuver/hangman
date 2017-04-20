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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author UGent
 */
public class HangmanPanelController extends GridPane {

    private Domeincontroller domeincontroller;

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

    public HangmanPanelController(Domeincontroller domeincontroller) {
        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("HangmanPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
            this.domeincontroller = domeincontroller;
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
    }

    @FXML
    private void gaNaarVolgendWoord(ActionEvent event) {
        startGame();
    }

    private void processAction(String stringchar) {
        boolean isvalid = domeincontroller.checkChar(stringchar);
        if (isvalid) {
            hangmanLabel.setText(domeincontroller.geefHangmanWoord(stringchar));
        } else {
            //update image hangman
            System.out.println("Wrong! Hangman update");
        }
        switch (domeincontroller.checkWinOfVerlies()) {
            case 1: {
                endgame(true);
                break;
            }
            case 2: {
                endgame(false);
                break;
            }
        }
    }

    private void startGame() {
        domeincontroller.geefVolgendWoord();
        //remove and generate
        if (!buttonOuterContainer.getChildren().isEmpty()) {
            buttonOuterContainer.getChildren().removeAll(buttonOuterContainer.getChildren());
        }
        generateButtons();
        hangmanLabel.setText(domeincontroller.geefHangmanWoord());
    }

    private void endgame(boolean win) {
        Label label;
        if (win) {
            label = new Label("Win!");
        } else {
            label = new Label("Lost!");
        }
        buttonContainer.getChildren().setAll(label);
    }
}
