package com.company;

import com.company.ClientServer.SocketClient;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Heartbeat implements Runnable {

    private Socket socket;
    private PrintWriter output;

    public Heartbeat(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            output = new PrintWriter(socket.getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }


        while (true){
            try {
                Thread.sleep(600);
                sendHeartbeat("/HeartbeatAlive");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            //output.println("Test det!");
        }
    }

    public void sendHeartbeat (String s) { output.println(s); }

}
