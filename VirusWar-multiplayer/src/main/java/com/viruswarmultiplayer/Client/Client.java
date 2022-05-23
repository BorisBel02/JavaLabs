package com.viruswarmultiplayer.Client;

import com.viruswarmultiplayer.Observer.Observer;

import java.io.*;

import java.net.Socket;


public class Client {

    private final PrintWriter out;
    public Client(String serverName, Integer port, Observer o) throws IOException {
        Socket socket = new Socket(serverName, port);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ServerListener listener = new ServerListener(in);
        listener.reg(o);
        listener.start();
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
    }
    public void send(String message){
        System.out.println("Client sending message " + message);
        out.println(message);
        out.flush();
    }

    

}
