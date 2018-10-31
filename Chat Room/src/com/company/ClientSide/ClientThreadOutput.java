package com.company.ClientSide;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThreadOutput extends Thread {

    private Socket socketClient;
    private PrintWriter output;
    public String message = "";

    public ClientThreadOutput (Socket socket) throws IOException {

        socketClient = socket;

        try {
            output = new PrintWriter(socketClient.getOutputStream(), true);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

    }

    public void run() {

        Heartbeat heartbeat = new Heartbeat();
        new Thread(heartbeat).start();

        do {

            //send message to server
            if (!message.equals("")) {
                output.println(message);
                message = "";
                System.out.println(" ");
            }

            //heartbeat message
            if (heartbeat.heartbeat) {
                output.println("/HEARTBEATALIVE");
                heartbeat.heartbeat = false;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (!message.equalsIgnoreCase("/quit"));
        if (message.equalsIgnoreCase("/quit")) {
            output.println("/quit");
        }

    }


}
