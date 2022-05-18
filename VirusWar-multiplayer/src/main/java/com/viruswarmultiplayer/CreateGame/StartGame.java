package com.viruswarmultiplayer.CreateGame;

import java.io.IOException;

public class StartGame {
    Controller controller;
    public StartGame() throws IOException {
        controller = new Controller();
        controller.start();
    }

    public int getPort(){
        return controller.getServer().getPort();
    }
    public String getHostName(){
        return controller.getServer().getIPv4Adress();
    }

}
