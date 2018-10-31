package com.company;

import com.company.ClientSide.SocketClient;
import com.company.ServerSide.SocketServer;

import java.io.IOException;
import java.util.Scanner;

public class StartMenu {

    public void menu (String args[]) throws IOException {

        System.out.println("Press 1 to connect to a server." +
                "\nPress 2 to host a server." +
                "\nPress anything else to quit.");

        Scanner scanner = new Scanner(System.in);

        switch (scanner.next()){

            case "1":
                SocketClient.main(args);
                return;

            case "2":
                SocketServer.main(args);
                return;

                default: return;

        }

    }

}
