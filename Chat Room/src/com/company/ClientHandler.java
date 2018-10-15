package com.company;

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

    public ClientHandler(Socket socket) {

        client = socket;

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

            //output.println("ECHO: " + received);
            SocketServer.writeToClients(received);

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

}
