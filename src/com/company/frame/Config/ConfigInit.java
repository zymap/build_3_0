package com.company.frame.Config;

import com.company.frame.mainServer.httpserver.Analyse;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by zy on 17-7-30.
 */
public class ConfigInit {


    public static String UPPERCHANNEL = "test1";
    public static String DATACHANNELL = "data";


    public static int HTTPPORT = 8888;
    public static int LONGCONNECTION_PORT = 9999;


    public static String REDIS_IP = "127.0.0.1";
    public static int REDIS_PORT = 6379;
    public static boolean REDIS_PUB_MUL= false;
    public static int REDIS_PUBS = 3;
    public static String REDIS_DATA_PUB1 = "data";
    public static String REDIS_DATA_PUB2 = "YYY";

    public static int REDIS_SUB_HEARTBEAT_TIME = 3000;



    public static String logFile="./Logger/log4j.propertites";
//    public static void logPropertyConfig(){
//        PropertyConfigurator.configure(logFile);
//    }
    static {
        PropertyConfigurator.configure(logFile);
    }

    public static Logger RECORD = Logger.getLogger("secondLogger");
}
