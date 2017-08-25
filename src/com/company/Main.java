package com.company;

import com.company.frame.Config.ConfigInit;
import com.company.frame.mainServer.httpserver.HttpServer;
import com.company.frame.mainServer.longconnection.ConnListen;
import com.company.frame.mainServer.longconnection.LongConnServer;
import com.company.util.Redis.JedisPool;
import com.company.util.ThreadUtil;

public class Main {
    public static void main(String[] args) {

        ThreadUtil.Run(new Runnable() {
            @Override
            public void run() {
                HttpServer httpserver = new HttpServer(ConfigInit.HTTPPORT);
                httpserver.start();
            }
        },"httpserver");
        ThreadUtil.Run(new Runnable() {
            @Override
            public void run() {
                JedisPool.createpool();
            }
        },"start createpool");
//        JedisPool.createpool();
        ThreadUtil.Run(new Runnable() {
            @Override
            public void run() {
                LongConnServer.ConnListen();
                LongConnServer longConnServer = new LongConnServer();
                longConnServer.start(ConfigInit.LONGCONNECTION_PORT);
            }
        },"longconnector");

    }
}
