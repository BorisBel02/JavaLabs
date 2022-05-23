package com.viruswarmultiplayer;

import com.viruswarmultiplayer.Client.Client;
import com.viruswarmultiplayer.Observer.Observer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Optional;


public class ClientWindowController implements Observer {
    Client client;

    @FXML
    GridPane grid;
    @FXML
    private Label CurrentAction;
    Image redVirus;
    Image blueVirus;
    Image killedRed;
    Image killedBlue;
    public ClientWindowController() throws IOException {
        TextInputDialog addressDialog = new TextInputDialog("localhost");
        addressDialog.setTitle("address");
        addressDialog.setContentText("Write address: ");

        TextInputDialog portDialog = new TextInputDialog("4004");
        portDialog.setTitle("port");
        portDialog.setContentText("Write port: ");
        Optional<String> address =  addressDialog.showAndWait();
        Optional<String> portStr = portDialog.showAndWait();

        if(address.isPresent() && portStr.isPresent()){
            client = new Client(address.get(), Integer.parseInt(portStr.get()), this);
        }
        blueVirus = new Image(getClass().getResourceAsStream("images/blue virus.png"));
        redVirus = new Image(getClass().getResourceAsStream("images/red virus.png"));
        killedBlue = new Image(getClass().getResourceAsStream("images/killed blue.png"));
        killedRed = new Image(getClass().getResourceAsStream("images/killed red.png"));

    }
    public void ActionOnKillButton(ActionEvent ev){
        CurrentAction.setText("kill");
    }
    public void ActionOnGrowButton(ActionEvent ev){
        CurrentAction.setText("grow");
    }

    public void ActionOnField(MouseEvent ev){
        Node source = (Node) ev.getSource();
        Integer col = GridPane.getColumnIndex(source);
        Integer row = GridPane.getRowIndex(source);

    }
    public void ActionOnGrid(MouseEvent ev){
        Node source = (Node) ev.getSource();
        Integer col = grid.getColumnIndex(source);
        Integer row = grid.getRowIndex(source);
    }
    public void ActionOnStartButton(ActionEvent ev){
        for(Integer i = 0; i < 10; ++i){
            for(Integer j = 0; j < 10; ++j){
                ImageView image = new ImageView();
                Integer finalI = i;
                Integer finalJ = j;
                image.setOnMouseClicked(mouseEvent -> {
                    if(mouseEvent.getClickCount() == 2){
                        client.send(CurrentAction.getText() + ' ' + finalI + ' ' + finalJ);
                    }
                });
                grid.add(image, i, j);
            }
        }
        grid.setGridLinesVisible(true);
        Button button = (Button)ev.getSource();
        button.setVisible(false);
        button.setDisable(true);
    }
    @Override
    public void update(Integer x, Integer y, Character sym){
        ImageView cell = null;
        ObservableList<Node> gridCells = grid.getChildren();

        for(Node node : gridCells){
            if(GridPane.getColumnIndex(node).equals(x) && GridPane.getRowIndex(node).equals(y)){
                cell = (ImageView) node;
            }
        }
        if(sym.equals('X')){
            cell.setImage(redVirus);
        }
        else if(sym.equals('O')){
            cell.setImage(blueVirus);
        }
        else if(sym.equals('x')){
            cell.setImage(killedRed);
        }
        else if(sym.equals('o')){
            cell.setImage(killedBlue);
        }
    }


}
