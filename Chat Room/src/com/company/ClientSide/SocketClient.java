package com.company.ClientSide;

import java.io.*;
import java.net.*;
import java.util.*;

public class SocketClient {

    public static void main(String[] args) {
        sendMessages();
    }

    private static void sendMessages() {

        Socket socket;
        Scanner scanner = new Scanner(System.in);
        int accepted = 0; //0 = no answer, 1 = accepted, 2 = denied

        socket = newConnection();

        try {
            Scanner userEntry = new Scanner(System.in);
            String message;

            SocketClientThread socketClientThread = new SocketClientThread(socket);
            socketClientThread.start();

            ClientThreadOutput clientThreadOutput = new ClientThreadOutput(socket);
            clientThreadOutput.start();

            //lock the client into a while loop until their username has been entered and validated from the server
            String username;
            while (accepted != 1) {

                while (true) {
                    username = scanner.nextLine();
                    if (username.matches("[a-zA-Z0-9_-]*$")) {
                        break;
                        }
                    System.out.println("Username must not contain other symbols than '-' or '_'");
                    }

                boolean canSend = true;
                clientThreadOutput.message="/USERNAME " + username;

                while (canSend) {
                    if (socketClientThread.validation==2) {
                        socketClientThread.validation=0;
                        canSend =false;
                        System.out.print("Username not valid or is already in use, try again: ");
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
                clientThreadOutput.message = message;
                if (message.equalsIgnoreCase("/quit")) {
                    Thread.sleep(1000);
                    break;
                }
            } while (true);
        }
        catch(IOException ioEx) {
            ioEx.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("\nClosing connection...");
                socket.close();
                System.exit(1);
            }
            catch (IOException ioEx) {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }

    public static Socket newConnection() {

        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket();
        String input;
        String ip;
        int port;
        boolean success=false;

        System.out.println("\nEnter ip address and port: ");

        while (!success) {

            input = scanner.next();
            ip="";
            port=0;

            if (input.equalsIgnoreCase("/quit")) {
                System.exit(1);
            }

            if (input.matches("[0-9-:.]+")) {

                for (int i = 0; i < input.length(); i++) {

                    try {
                        if (input.substring(i, i + 1).matches("[:]")) {
                            try {
                                port = Integer.parseInt(input.substring(i + 1, input.length()));
                                try {
                                    socket = new Socket(ip, port);
                                    success = true;
                                    break;
                                } catch (IOException ioEx) {
                                    System.out.println("Couldn't connect to server");
                                } catch (IllegalArgumentException ioEx) {
                                }
                            } catch (NumberFormatException ioEx) {
                                break;
                            }
                        } else {
                            ip = ip + input.substring(i, i + 1);
                        }
                    } catch (StringIndexOutOfBoundsException ioEx) {
                        break;
                    }
                }
            }
            if (success) {break;}
            System.out.println("Not a valid ip address, please try again: ");
        }
        return socket;
    }


}
