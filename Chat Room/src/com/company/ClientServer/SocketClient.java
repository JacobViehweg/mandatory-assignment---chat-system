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
        Scanner scanner = new Scanner(System.in);
        int accepted = 0; //0 = no answer, 1 = accepted, 2 = denied

        try {
            socket = new Socket(host,PORT);

            PrintWriter networkOutput = new PrintWriter(socket.getOutputStream(),true);

            Scanner userEntry = new Scanner(System.in);
            String message;

            SocketClientThread socketClientThread = new SocketClientThread(socket);
            socketClientThread.start();

            //lock the client into a while loop until their username has been entered and validated from the server
            String username;
            while (accepted != 1) {
                username = scanner.nextLine();
                boolean canSend = true;
                networkOutput.println("/USERNAME " + username);

                while (canSend) {
                    if (socketClientThread.validation==2) {
                        socketClientThread.validation=0;
                        canSend =false;
                        System.out.print("Username already taken. Please enter another username: ");
                    }
                    if (socketClientThread.validation==1) {
                        accepted = 1;
                        break;
                    }
                }

            }
            //begin chatting
            System.out.print("Enter message ('/Quit' to exit): \n");

            do {

                message = userEntry.nextLine();

                networkOutput.println(message);
                System.out.println(" ");
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
