package com.company.util.Redis;

/**
 * Created by zy on 17-8-23.
 */
public class RedisMessage {
    public static String getHead(String str){
        String[] s = str.split("\\|");
        return s[0];
    }

    public static String getBody(String str){
        String[] s = str.split("\\|");
        return s[1];
    }
}
