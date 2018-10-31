package com.company.ServerSide;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientHandler extends Thread  {

    public Socket client;
    private Scanner input;
    private PrintWriter output;
    private String username = "";
    private long handlerID;
    private Date heartbeatRecieved;

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

        String received = "";
        output.println("" + CommandCheck.countUsers());
        heartbeatRecieved = new Date();

        do {
            //tries to recieve a new string (maybe make the catch function not break the entire loop (to allow reconnection) )
            try {
                received = input.nextLine();
            } catch (NoSuchElementException ioEx) {
                System.out.println("Connection to [" + getUsername() + "] lost, trying to reconnect...");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //checks the recieved messages
            if (!CommandCheck.slashCheck(received)){
                Date date = new Date();
                String time = date.getHours() + ":" + date.getMinutes();

                SocketServer.writeToClientsNoEcho("[" + time + " | " + getUsername() + "] " + received,getHandlerID());
            } else {
                received = CommandCheck.message(received,this);
                if (!received.equalsIgnoreCase("/HEARTBEAT")) {
                    SocketServer.writeToSelf(received, this.handlerID);
                }
            }
        } while (!received.equalsIgnoreCase("/QUIT") && (new Date().getTime()-this.heartbeatRecieved.getTime()<300000));

        try {
            if (client!=null) {
                System.out.println("Closing down connection to [" + this.username + "]...");
                SocketServer.writeToClients(getUsername() + " has left the room");
                client.close();
            }
        }
        catch (IOException ioEx) {
            System.out.println("Unable to disconnect!");
        }
        SocketServer.removeClientHandler(getHandlerID());
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHeartbeatRecieved(Date heartbeatRecieved) {
        this.heartbeatRecieved = heartbeatRecieved;
    }

}
