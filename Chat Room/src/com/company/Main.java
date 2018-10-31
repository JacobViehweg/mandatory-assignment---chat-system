package com.company;

import com.company.ClientSide.SocketClient;
import com.company.Window.Window;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String s = "asdf1234";

        System.out.println(s.matches("[a-zA-Z0-9_-]*$"));

        Window window = new Window();
        window.start();

        SocketClient socketClient = new SocketClient();
        socketClient.main(args);

    }




}

