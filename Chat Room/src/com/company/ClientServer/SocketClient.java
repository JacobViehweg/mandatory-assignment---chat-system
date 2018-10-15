package com.company.ClientServer;

import com.company.SocketClientThread;

import java.io.*;
import java.net.*;
import java.util.*;

public class SocketClient {

    private static InetAddress host;
    private static final int PORT = 1234;

    public static void main(String[] args) {

        try {
            host = InetAddress.getLocalHost();

        }
        catch (UnknownHostException uhEx) {
            System.out.println("\nHost ID not Found!\n");
            System.exit(1);
        }



        sendMessages();
    }

    private static void sendMessages() {

        Socket socket = null;

        try {
            socket = new Socket(host,PORT);

            Scanner networkInput = new Scanner(socket.getInputStream());

            PrintWriter networkOutput = new PrintWriter(socket.getOutputStream(),true);

            Scanner userEntry = new Scanner(System.in);
            String message, response;

            SocketClientThread socketClientThread = new SocketClientThread(socket);
            socketClientThread.start();

            System.out.print("Enter message ('/Quit' to exit): ");

            do {

                message = userEntry.nextLine();

                networkOutput.println(message);
                System.out.println(" ");
                //response = networkInput.nextLine();

                //System.out.println("\nSERVER> " + response);
            } while (!message.equalsIgnoreCase("/quit"));
        }
        catch(IOException ioEx) {
            ioEx.printStackTrace();
        }
        finally {
            try {
                System.out.println("\nClosing connection...");
                socket.close();
            }
            catch (IOException ioEx) {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }
}
