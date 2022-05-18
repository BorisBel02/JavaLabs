package com.viruswarmultiplayer.Client;

import com.viruswarmultiplayer.Observer.Observable;
import com.viruswarmultiplayer.Observer.Observer;

import java.io.BufferedReader;
import java.io.IOException;


public class ServerListener extends Thread implements Observable {
    BufferedReader serverIn;
    Observer observer;
    public ServerListener(BufferedReader in){
        serverIn = in;
    }
    public void run(){
        try {
            System.out.println("listener run");
            String message = serverIn.readLine();
            System.out.println("listener read something: " + message);
            String[] parsedMessage = message.split(" ");
            if(parsedMessage[0].equals("update")){
                System.out.println("listener got update msg from server");
                notifySubscribers(Integer.parseInt(parsedMessage[1]), Integer.parseInt(parsedMessage[2]), parsedMessage[3].charAt(0));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
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
