package com.company.ClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SocketServer {

    private Scanner scanner = new Scanner(System.in);
    private String userName = "";
    private int serverPort = 6000;
    private String serverIP = "127.0.0.1";
    private String name = "Supermand";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
    private Date date = new Date();

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start() throws IOException {
        serverSocket = new ServerSocket(this.serverPort);
        clientSocket = serverSocket.accept();

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        run();

    }

    public void run() throws IOException {

        while (true){

            if (scanner.hasNextLine()){
            out.println(scanner.nextLine());}



            String msg = in.readLine() + "\n" + in.readLine();
            System.out.println(msg);
        }

    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

}
