package com.viruswarmultiplayer.Client;

import com.viruswarmultiplayer.ClientFrame;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


public class Connect extends Thread{

    @Override
    public void run() {
        try{
            new ClientFrame();
        }catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Connect");
            alert.setHeaderText("Connection failed.\n" + e);
            alert.show();
        }
        // old code
        /*Stage clientStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/ClientFrame.fxml"));
            Scene scene = new Scene(root);
            clientStage.setScene(scene);
            clientStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Connect");
            alert.setHeaderText("Connection failed.\n" + e);
            alert.show();
        }*/
    }
}
