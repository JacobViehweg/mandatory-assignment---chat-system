package com.company.ClientSide;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Heartbeat implements Runnable {

    public boolean heartbeat;

    public Heartbeat(){
        heartbeat=true;
    }

    @Override
    public void run() {

        while (true){

            if (!heartbeat) {

                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                heartbeat = true;

            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
