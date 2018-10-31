package com.company;

import com.company.ClientSide.SocketClient;
import com.company.Window.Window;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Window window = new Window();
        window.start();

        SocketClient socketClient = new SocketClient();
        socketClient.main(args);

    }




}

