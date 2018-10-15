package com.company;

import java.sql.SQLOutput;

public class CommandCheck {

    public static String message (String s,ClientHandler client){

        if(s.charAt(0)=='/'){

            String[] sArray = s.split(" ");

            switch (sArray[0]){

                // ChangeName
                case "/ChangeName": if (sArray.length>2){
                    changeName(sArray[1],client);
                    System.out.println("Name set to: " + sArray[1]);
                } else { System.out.println("No proper name given.");}
                return "";
                //

                // Help
                case "/Help":
                    System.out.println("List of commands:" +
                            "/Help" +
                            "/ChangeName" +
                            "/Quit");
                    return "";
                //

                // Default
                default:
                    System.out.println("Invalid command type /Help for commands");
                    return "";
                //

            }
        }
        return s;
    }

    public static void changeName(String s, ClientHandler client){

        client.setName(s);

    }


}
