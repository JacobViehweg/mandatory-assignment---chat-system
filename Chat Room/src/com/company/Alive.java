package com.company;

import com.company.ClientServer.SocketServer;

public class Alive implements Runnable {

    public long handlerID;

    public Alive(long handlerID) {
        this.handlerID = handlerID;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SocketServer.living.remove(this);
    }


}
