package com.viruswarmultiplayer;

import com.viruswarmultiplayer.Client.Connect;
import com.viruswarmultiplayer.CreateGame.StartGame;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StartFrameController {
    @FXML
    private TitledPane mainStage;
    public void ActionOnConnectButton(ActionEvent event){
        Platform.runLater(new Connect());
    }

    public void ActionOnCreateGameButton(ActionEvent e){
        Stage stage = (Stage) mainStage.getScene().getWindow();
        StartGame game;
        try {
            game = new StartGame();
        } catch (IOException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Start Game");
            alert.setHeaderText("Starting game failed.");
            alert.show();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Start Game");
        alert.setHeaderText("Game Started\nPort: " + game.getPort() + "\nHost name: " + game.getHostName());
 //       alert.show();

    }
    public void ActionOnHelpButton(ActionEvent e){
        //TODO
    }
}
