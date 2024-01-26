/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Lucas
 */
public class SocketModel implements Runnable {

    private Socket socket;
    private InputStream inSocket;
    private OutputStream outSocket;

    public SocketModel(Socket socket) throws IOException {
        this.socket = socket;
        this.inSocket = socket.getInputStream();
        this.outSocket = socket.getOutputStream();
    }

    @Override
    public void run() {
        
            

    }

    public void sendMessage(String message) {

    }

    public void close() throws IOException {
        socket.close();
    }
}
