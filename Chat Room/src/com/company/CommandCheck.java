package com.company;

import java.sql.SQLOutput;

public class CommandCheck {

    public static String message (String s,ClientHandler client){

        if(slashCheck(s)){

            String[] sArray = s.split(" ");

            switch (sArray[0].toUpperCase()){

                // ChangeName
                case "/CHANGENAME": if (sArray.length>1){

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

                // Default
                default:
                    return "Invalid command type /Help for commands";
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

}
