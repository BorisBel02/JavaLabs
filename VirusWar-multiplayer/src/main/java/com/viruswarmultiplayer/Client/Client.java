package com.viruswarmultiplayer.Client;

import com.viruswarmultiplayer.Observer.Observer;

import java.io.*;
import java.net.Socket;


public class Client {
    private final Socket socket;

    private final PrintWriter out;
    private final BufferedReader in;

    public Client(String serverName, Integer port, Observer o) throws IOException {
        socket = new Socket("localhost", port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ServerListener listener = new ServerListener(in);
        listener.reg(o);
        listener.start();
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
    }
    public void send(String message){
        out.println(message);
    }

    

}
