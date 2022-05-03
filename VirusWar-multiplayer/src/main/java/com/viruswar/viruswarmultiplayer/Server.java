package com.viruswar.viruswarmultiplayer;

import com.viruswar.viruswarmultiplayer.Exception.GameException;
import com.viruswar.viruswarmultiplayer.GameModel.Game;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int port;
    private final String IPv4Adress;

    ServerSocket server;
    Socket player1Socket;
    BufferedReader player1Input;
    PrintWriter player1Output;

    Socket player2Socket;
    BufferedReader player2Input;
    PrintWriter player2Output;

    public Server() throws IOException {
        server = new ServerSocket();
        port = server.getLocalPort();
        IPv4Adress = server.getInetAddress().getHostAddress();

        player1Socket = server.accept();
        player1Input = new BufferedReader(new InputStreamReader(player1Socket.getInputStream()));
        player1Output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(player1Socket.getOutputStream())));

        player2Socket = server.accept();
        player2Input = new BufferedReader(new InputStreamReader(player2Socket.getInputStream()));
        player2Output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(player2Socket.getOutputStream())));
    }

    public void notified(String type, Character cell,  Integer x, Integer y){
        player2Output.println("notification " + type + " " + x + " " + y + " " + cell);
        player1Output.println("notification " + type + " " + x + " " + y + " " + cell);
    }
    private void act(String playerAction, Game game){
        String[] action = playerAction.split(" ");
        if (action[0].equals("kill")) {
            game.killEnemy(Integer.parseInt(action[1]), Integer.parseInt(action[2]));
        } else if (action[0].equals("grow")) {
            game.grow(Integer.parseInt(action[1]), Integer.parseInt(action[2]));
        }
    }
    public void start() throws IOException{
        Game game = new Game();

        String playerAction;

        while (true) {
            if (game.getTurn() == 'X') {

                playerAction = player1Input.readLine();
                try {
                    act(playerAction, game);
                    player1Output.println("status accepted");
                }catch (GameException e){
                    player1Output.println("status denied");
                }
            } else {
                playerAction = player2Input.readLine();
                try {
                    act(playerAction, game);
                    player2Output.println("status accepted");
                }catch (GameException e){
                    player2Output.println("status denied");
                }
            }

            if(game.getScore().getKey() == 0){
                notified("end", 'X', null, null);
                break;
            }
            if(game.getScore().getValue() == 0){
                notified("end", 'O', null, null);
                break;
            }

        }

    }
}
