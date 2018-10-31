package com.company.ServerSide;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class SocketServer {

    private static ServerSocket serverSocket;
    private static final int PORT = 1234;
    public static List<ClientHandler> clientHandlerList = new ArrayList<>();
    public static List<Alive> living = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        //KillTheDead killTheDead = new KillTheDead();
        //new Thread(killTheDead).start();

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

            ClientHandler handler = new ClientHandler(client);
            handler.start();
            clientHandlerList.add(handler);

            System.out.println("\nNew client accepted.\n");

        } while (true);


    }

    public static void writeToClients (String message) {

        for (ClientHandler handler:clientHandlerList) {

            handler.sendMessage(message);
        }
    }

    public static void writeToClientsNoEcho (String message, long ID) {

        for (ClientHandler handler:clientHandlerList) {

            if (handler.getHandlerID() != ID) {
                handler.sendMessage(message);
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

    public static void removeClientHandler (long ID) {

        for (ClientHandler clientHandler:clientHandlerList) {
            if (clientHandler.getHandlerID() == ID) {
                clientHandlerList.remove(clientHandler);
                break;
            }

        }


    }

    public static String printOnlineUsers () {

        int userCount = 0;
        String users = "";
        int newLine = 5;

        for (ClientHandler handler:clientHandlerList) {

            if (!handler.getUsername().equals("")) {
                userCount++;

                if (newLine == 0) {
                    newLine = 8;
                    users = users + ",\n" + handler.getUsername();
                } else if (users.equals("")) {
                    users = users + handler.getUsername();
                }  else {
                    users = users + ", " + handler.getUsername();
                }
                newLine = newLine-1;
            }
        }

        String userList = "Users currently online (" + userCount + "): " + users;

        return userList;

    }




}