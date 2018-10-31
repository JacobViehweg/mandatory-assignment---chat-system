package com.company.UnusedClasses;

import com.company.ServerSide.SocketServer;

public class Alive implements Runnable {

    public long handlerID;

    public Alive(long handlerID) {
        this.handlerID = handlerID;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SocketServer.living.remove(this);
    }


}
