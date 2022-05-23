package com.viruswarmultiplayer.CreateGame;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket server;
    Socket player1Socket;
    BufferedReader player1Input;
    PrintWriter player1Output;

    Socket player2Socket;
    BufferedReader player2Input;
    PrintWriter player2Output;

    public Server() throws IOException {
        server = new ServerSocket(0);
    }
    public void establishConnections() throws IOException{
        player1Socket = server.accept();
        player1Input = new BufferedReader(new InputStreamReader(player1Socket.getInputStream()));
        player1Output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(player1Socket.getOutputStream())));

        player2Socket = server.accept();
        player2Input = new BufferedReader(new InputStreamReader(player2Socket.getInputStream()));
        player2Output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(player2Socket.getOutputStream())));
        System.out.println("players connected");
        server.close();
    }

    public void notify(String type, Character cell,  Integer x, Integer y){
        System.out.println("notifying...");
        player1Output.println(type + " " + x + " " + y + " " + cell);
        player2Output.println(type + " " + x + " " + y + " " + cell);
        player1Output.flush();
        player2Output.flush();
    }

    public String recv1() throws IOException {
        System.out.println("waiting action from player 1");
        return player1Input.readLine();
    }
    public String recv2() throws IOException {
        System.out.println("waiting action from player 2");
        return player2Input.readLine();
    }

    public void send1(String message) {
        player1Output.println(message);
        player1Output.flush();
    }
    public void send2(String message){
        player2Output.println(message);
        player2Output.flush();
    }


    public int getPort() {
        return server.getLocalPort();
    }
    public String getIPv4Adress() {
        return server.getInetAddress().getHostAddress();
    }
}
