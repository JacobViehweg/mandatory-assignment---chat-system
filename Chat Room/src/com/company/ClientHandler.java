package com.company;

import com.company.ClientServer.SocketClient;
import com.company.ClientServer.SocketServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler extends Thread  {

    private Socket client;
    private Scanner input;
    private PrintWriter output;
    private String username;
    private long handlerID;

    public ClientHandler(Socket socket) {

        client = socket;
        generateID();

        try {
            input = new Scanner(client.getInputStream());
            output = new PrintWriter(client.getOutputStream(),true);
        }
        catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    public void run() {

        String received;

        do {
            received = input.nextLine();

            //SocketServer.writeToClients(received);
            SocketServer.writeToClientsNoEcho(received,getHandlerID());

            System.out.println("Recieved: " + received);

        } while (!received.equals("QUIT"));

        try {
            if (client!=null) {
                System.out.println("Closing down connection...");
                client.close();
            }
        }
        catch (IOException ioEx) {
            System.out.println("Unable to disconnect!");
        }
    }

    public void sendMessage (String message) {
        output.println(message);
    }

    public void generateID() {
        long newID = 1;

        if (SocketServer.clientHandlerList.size() != 0) {
            newID = SocketServer.clientHandlerList.get(SocketServer.clientHandlerList.size()-1).getHandlerID()+1;
        }
        setHandlerID(newID);
    }

    public long getHandlerID() {
        return handlerID;
    }

    public void setHandlerID(long handlerID) {
        this.handlerID = handlerID;
    }
}
