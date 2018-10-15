package com.company;

import com.company.ClientServer.SocketClient;
import com.company.ClientServer.SocketServer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }

    public static void clientStart () throws IOException {

        SocketClient socketClient = new SocketClient();
        socketClient.startConnection("127.0.0.1",6000);
        socketClient.chat();
    }

    public static void serverStart() throws IOException {

        SocketServer socketServer = new SocketServer();
        socketServer.start();
        socketServer.stop();

    }



}

