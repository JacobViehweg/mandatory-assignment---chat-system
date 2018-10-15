package com.company.ClientServer;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketServer {

    private static ServerSocket serverSocket;
    private static final int PORT = 1234;

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
        } while (true);


    }
}

class ClientHandler extends Thread  {

    private Socket client;
    private Scanner input;
    private PrintWriter output;

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

            output.println("ECHO: " + received);
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
}
