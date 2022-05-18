package com.viruswarmultiplayer.CreateGame;

import com.viruswarmultiplayer.GameModel.Game;
import com.viruswarmultiplayer.Exception.GameException;
import com.viruswarmultiplayer.Observer.Observer;

import java.io.IOException;

public class Controller extends Thread implements Observer {
    public Server getServer() {
        return server;
    }

    Server server;
    Game game;
    private void act(String playerAction){
        String[] action = playerAction.split(" ");
        if (action[0].equals("kill")) {
            game.killEnemy(Integer.parseInt(action[1]), Integer.parseInt(action[2]));
        } else if (action[0].equals("grow")) {
            game.grow(Integer.parseInt(action[1]), Integer.parseInt(action[2]));
        }
    }
    @Override
    public void run(){
        try {
            server = new Server();
            game = new Game();
            game.reg(this);
            server.establishConnections();
            update(0, 0, 'X');
            update(9,9, 'O');
            while (true) {
                String playerAction;
                if (game.getTurn() == 'X') {

                    playerAction = server.recv1();
                    try {
                        act(playerAction);
                        server.send1("status accepted");
                    } catch (GameException e) {
                        server.send1("status denied");
                    }
                } else {
                    playerAction = server.recv2();
                    try {
                        act(playerAction);
                        server.send2("status accepted");
                    } catch (GameException e) {
                        server.send2("status denied");
                    }
                }

                if (game.getScore().getKey() == 0) {
                    server.notify("gameover", '1', 0, 0);
                    break;
                } else if (game.getScore().getValue() == 0) {
                    server.notify("gameover", '2', 0, 0);
                    break;
                }
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Integer x, Integer y, Character cell) {
        server.notify("update", cell, x, y);
    }
}
