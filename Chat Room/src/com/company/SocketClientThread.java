package com.company;

import com.company.Window.Window;

import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SocketClientThread extends Thread {

    private Socket socketClient;
    private Scanner input;
    public int validation = 0; //0 = no answer from server, 1 = accepted, 2 = rejected

    public SocketClientThread (Socket socket) throws IOException {

        socketClient = socket;

        Heartbeat heartbeat = new Heartbeat(socket);
        new Thread(heartbeat).start();

        try {
            input = new Scanner(socketClient.getInputStream());
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

    }

    public void run() {

        //System.out.println("Starting thread");

        String received;

        received = input.nextLine();
        System.out.println("Current users online: " + received);
        System.out.print("Please enter your desired username: ");

        while(true) {

            try {
                received = input.nextLine();
            } catch (NoSuchElementException ioEx) {
                break;
            }

            if (received.equalsIgnoreCase("true")) {
                validation = 1;
                break;
            }
            if (received.equalsIgnoreCase("false")) {
                validation = 2;
                }
            }


        while(true) {

            try {
                received = input.nextLine();
            } catch (NoSuchElementException ioEx) {
                break;
            }

            System.out.println(received);
            Window.message=Window.message + "<br>" +received;
        }


    }



}
