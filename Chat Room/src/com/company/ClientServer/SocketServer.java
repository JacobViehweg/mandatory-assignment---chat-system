package com.company.ClientServer;

import com.company.ClientHandler;
import com.company.CommandCheck;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SocketServer {

    private static ServerSocket serverSocket;
    private static final int PORT = 1234;
    public static List<ClientHandler> clientHandlerList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        System.out.println("Starting server...");

        try
        {
            serverSocket = new ServerSocket(PORT);
        }
        catch (IOException ioEx)
        {
            System.out.println("\nUnable to set up port!");
            System.exit(1);
        }

        do {

            Socket client = serverSocket.accept();

            System.out.println("\nNew client accepted.\n");

            ClientHandler handler = new ClientHandler(client);
            handler.start();
            clientHandlerList.add(handler);
        } while (true);


    }

    public static void writeToClients (String message) {

        for (ClientHandler handler:clientHandlerList) {

            handler.sendMessage(CommandCheck.message(message,handler));
        }
    }

    public static void writeToClientsNoEcho (String message, long ID) {

        for (ClientHandler handler:clientHandlerList) {

            if (handler.getHandlerID() != ID) {
                handler.sendMessage(CommandCheck.message(message,handler));
            }
        }

    }

    public static void writeToSelf (String message, long ID) {

        for (ClientHandler handler:clientHandlerList) {

            if (handler.getHandlerID() == ID) {
                handler.sendMessage(CommandCheck.message(message,handler));
            }
        }

    }




}