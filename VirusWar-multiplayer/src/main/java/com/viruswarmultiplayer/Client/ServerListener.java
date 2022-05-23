package com.viruswarmultiplayer.Client;

import com.viruswarmultiplayer.Observer.Observable;
import com.viruswarmultiplayer.Observer.Observer;
import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.IOException;


public class ServerListener extends Thread implements Observable {
    BufferedReader serverIn;
    Observer observer;
    public ServerListener(BufferedReader in){
        this.setDaemon(true);
        serverIn = in;
    }
    public void run(){
        try {
            System.out.println("listener run");
            while(true) {
                String message = serverIn.readLine();
                System.out.println("listener got message: " + message);
                String[] parsedMessage = message.split(" ");
                if (parsedMessage[0].equals("update")) {
                    notifySubscribers(Integer.parseInt(parsedMessage[1]), Integer.parseInt(parsedMessage[2]), parsedMessage[3].charAt(0));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(e.toString());
            alert.show();
        }

    }

    @Override
    public void reg(Observer o) {
        observer = o;
    }

    @Override
    public void notifySubscribers(Integer x, Integer y, Character cell) {
        observer.update(x, y, cell);
    }
}
