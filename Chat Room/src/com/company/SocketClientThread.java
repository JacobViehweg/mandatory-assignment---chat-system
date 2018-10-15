package com.company;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketClientThread extends Thread {

    private Socket socketClient;
    private Scanner input;

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
            System.out.println(received);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }


    }



}
