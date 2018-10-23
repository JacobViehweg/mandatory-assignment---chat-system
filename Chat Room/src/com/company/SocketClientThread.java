package com.company;

import com.company.Window.Window;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketClientThread extends Thread {

    private Socket socketClient;
    private Scanner input;
    public int validation = 0; //0 = no answer from server, 1 = accepted, 2 = rejected

    public SocketClientThread (Socket socket) throws IOException {

        socketClient = socket;
        try {
            input = new Scanner(socketClient.getInputStream());
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

    }

    public void run() {

        System.out.println("Starting thread");

        String received;

        while(true) {
            received = input.nextLine();
            if (received.equalsIgnoreCase("true")) {
                validation = 1;
                break;
            }
            if (received.equalsIgnoreCase("false")) {
                validation = 2;
                }
            }


        while(true) {
            received = input.nextLine();
            System.out.println(received);
            Window.message=Window.message + "<br>" +received;
        }


    }



}
