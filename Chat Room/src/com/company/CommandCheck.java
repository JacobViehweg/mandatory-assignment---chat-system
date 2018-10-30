package com.company;

import com.company.ClientServer.SocketServer;

import java.sql.SQLOutput;

public class CommandCheck {

    public static String message (String s,ClientHandler client){

        if(slashCheck(s)){

            String[] sArray = s.split(" ");

            switch (sArray[0].toUpperCase()){

                // ChangeName
                case "/CHANGENAME": if (sArray.length>1){
                    for (ClientHandler handler:SocketServer.clientHandlerList) {
                        if (handler.getUsername().equalsIgnoreCase(sArray[1])) {
                            return "Username already taken";
                        }
                    }
                    SocketServer.writeToClientsNoEcho(client.getUsername() + " changed their name to " + sArray[1], client.getHandlerID());
                    changeName(sArray[1],client);
                    s = "Name set to: " + sArray[1];
                    System.out.println();
                } else { s = "No proper name given.";}
                return s;
                //

                // MyInfo
                case "/MYINFO":
                    return client.getUsername() + "\n" +
                            client.getHandlerID();

                // Help
                case "/HELP":
                    s = "List of commands:\n" +
                            "/Help\n" +
                            "/ChangeName\n" +
                            "/MyInfo\n" +
                            "/Quit";
                    return s;
                //

                // Validate username
                case "/USERNAME": if (sArray.length>1) {
                    s = "true";
                    String username = sArray[1];
                    for (ClientHandler clientHandler : SocketServer.clientHandlerList) {
                        if (clientHandler.getUsername().equalsIgnoreCase(username)) {
                            s = "false";
                        }
                    }
                    if (s.equalsIgnoreCase("true")) {
                        changeName(username, client);
                        SocketServer.writeToClientsNoEcho(username + " has joined the room", client.getHandlerID());
                    }
                }
                    return s;
                //

                // Return user list
                case "/USERS":

                    return SocketServer.printOnlineUsers();

                //

                // close the client
                case "/QUIT":
                    return "/QUIT";
                //

                // Default
                default:
                    return "Invalid command, type /Help for commands";
                //

            }
        }
        return s;
    }

    public static void changeName(String s, ClientHandler client){

        client.setUsername(s);

    }

    public static boolean slashCheck (String s){
        return (s.charAt(0)=='/');
    }

    public static int countUsers () {

        int userCount = 0;

        for (ClientHandler clientHandler:SocketServer.clientHandlerList) {

            if (!clientHandler.getUsername().equalsIgnoreCase("")) {
                userCount++;
            }
        }

        return userCount;
    }

}
