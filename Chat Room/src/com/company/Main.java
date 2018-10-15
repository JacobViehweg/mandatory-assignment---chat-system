package com.company;

import com.company.ClientServer.SocketClient;
import com.company.ClientServer.SocketServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket();
        ClientHandler clientHandler = new ClientHandler(socket);
        CommandCheck commandCheck = new CommandCheck();

        System.out.println(commandCheck.message("/ChangeName",clientHandler));

    }




}

