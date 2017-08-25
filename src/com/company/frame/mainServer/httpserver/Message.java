package com.company.frame.mainServer.httpserver;

/**
 * Created by zy on 17-7-16.
 */
public class Message {

    public static String getBody(String str){
        String[] temp = str.split("/");
        return temp[1];
    }

    public static String getHead(String str) {
        String[] temp = str.split("/");
        return temp[0];
    }
}
