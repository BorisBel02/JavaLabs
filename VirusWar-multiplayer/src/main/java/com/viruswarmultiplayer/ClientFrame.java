package com.viruswarmultiplayer;

import com.viruswarmultiplayer.Client.Client;
import com.viruswarmultiplayer.Observer.Observer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ClientFrame implements Observer {
    Stage stage = new Stage();
    Label currentAction = new Label();

    Client client;
    ImagePattern blue_virus;
    ImagePattern red_virus;
    ImagePattern killed_blue;
    ImagePattern killed_red;


    Rectangle[][] field = new Rectangle[10][10];

    int rectSize = 40;
    public ClientFrame() throws IOException {
        BorderPane border = new BorderPane();

        HBox control = new HBox();
        control.setPrefHeight(40);
        control.setSpacing(10.0);
        control.setAlignment(Pos.BASELINE_CENTER);

        Button kill = new Button("KILL");
        Button grow = new Button("GROW");

        kill.setOnMouseClicked(
                mouseEvent -> {
                    currentAction.setText("kill");
                }
        );
        grow.setOnMouseClicked(
                mouseEvent -> {
                    currentAction.setText("grow");
                }
        );
        control.getChildren().addAll(kill, grow);
        border.setBottom(control);

        currentAction.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
        border.setTop(currentAction);

        stage.setScene(new Scene(border, 600, 600));
        stage.setTitle("VirusWar");
        stage.setResizable(true);

        blue_virus = new ImagePattern(new Image(getClass().getResourceAsStream("images/blue virus.png")));
        red_virus = new ImagePattern(new Image(getClass().getResourceAsStream("images/red virus.png")));
        killed_blue = new ImagePattern(new Image(getClass().getResourceAsStream("images/killed blue.png")));
        killed_red = new ImagePattern(new Image(getClass().getResourceAsStream("images/killed red.png")));

        border.setCenter(buildGrid());

        TextInputDialog addressDialog = new TextInputDialog("localhost");
        addressDialog.setTitle("address");
        addressDialog.setContentText("Write address: ");

        TextInputDialog portDialog = new TextInputDialog("4004");
        portDialog.setTitle("port");
        portDialog.setContentText("Write port: ");
        Optional<String> address =  addressDialog.showAndWait();
        Optional<String> portStr = portDialog.showAndWait();
        client = new Client(address.get(), Integer.parseInt(portStr.get()), this);

        stage.getIcons().add(red_virus.getImage());
        stage.show();
    }
    private Rectangle buildRectangle(int x, int y, int size) {
        Rectangle rect = new Rectangle();
        rect.setX(x * size);
        rect.setY(y * size);
        rect.setHeight(size);
        rect.setWidth(size);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);
        return rect;
    }
    private EventHandler<MouseEvent> mouseEvent(Group panel){
        return event -> {
            Rectangle rect = (Rectangle) event.getTarget();
            int x = (int)rect.getX()/rectSize;
            int y = (int)rect.getY()/rectSize;
            System.out.println("Event on " + x + y + " cell");
            client.send(currentAction.getText() + ' ' + x + ' ' + y);
        };
    }
    private Group buildGrid() {
        Group panel = new Group();
        for (int y = 0; y != 10; y++) {
            for (int x = 0; x != 10; x++) {
                Rectangle rectangle = this.buildRectangle(x, y, rectSize);
                this.field[x][y] = rectangle;
                panel.getChildren().add(rectangle);
                rectangle.setOnMouseClicked(this.mouseEvent(panel));
            }
        }
        return panel;
    }


    @Override
    public void update(Integer x, Integer y, Character cell) {
        System.out.println("frame updating");
        if(cell.equals('X')){
            field[x][y].setFill(red_virus);
        }
        else if(cell.equals('O')){
            field[x][y].setFill(blue_virus);
        }
        else if(cell.equals('x')){
            field[x][y].setFill(killed_red);
        }
        else if(cell.equals('o')){
            field[x][y].setFill(killed_blue);
        }
    }
}
