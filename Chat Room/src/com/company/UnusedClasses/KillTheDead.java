package com.company.UnusedClasses;

import com.company.ServerSide.ClientHandler;
import com.company.ServerSide.SocketServer;

import java.util.Iterator;

public class KillTheDead implements Runnable {

    @Override
    public void run() {

        while(true){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

            Iterator<ClientHandler> clientHandlerIterator = SocketServer.clientHandlerList.iterator();
            Iterator<Alive> aliveIterator = SocketServer.living.iterator();

            while(clientHandlerIterator.hasNext()){
                Boolean pulseCheck = false;
                ClientHandler clientHandler = clientHandlerIterator.next();

                while(aliveIterator.hasNext()){
                    Alive alive = aliveIterator.next();

                    if(clientHandler.getHandlerID() == alive.handlerID){
                        pulseCheck = true;
                    }

                }
                System.out.println(pulseCheck);
                if(!pulseCheck){
                    clientHandlerIterator.remove();
                }

            }


        }

    }

}
