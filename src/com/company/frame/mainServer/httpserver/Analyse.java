package com.company.frame.mainServer.httpserver;

import com.company.frame.Config.ConfigInit;
import com.company.pojo.upper.Upper;
import com.company.util.Redis.Redis;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import sun.security.provider.ConfigFile;

import java.lang.reflect.Method;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zy on 17-7-21.
 */
public class Analyse implements Runnable {
    private static Logger logger = Logger.getLogger(Analyse.class);

    private static Logger record = Logger.getLogger("secondLogger");
    private String reqestURI;
    private String message;
    private static String HEAD = "Json|";


    private final static String PACKAGE = "com.company.pojo.upper.Upper";
    private final static String MESSAGE = "message";
    private final static ReentrantLock lock = new ReentrantLock();

    public Analyse(String uri, String message){
        this.reqestURI = uri;
        this.message = message;

    }

    public Analyse(){}

    public String getReqestURI() {
        return reqestURI;
    }

    public void setReqestURI(String reqestURI) {
        this.reqestURI = reqestURI;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void DealSet() {
        lock.lock();
        try {
            switch (reqestURI){
                case MESSAGE:
                    record.info("ConfigInit:"+ConfigInit.DATACHANNELL+"/t|"+message+"\\\\"+reqestURI);
                    logger.info("====begin====\t"+ ConfigInit.DATACHANNELL);
                    if (ConfigInit.REDIS_PUB_MUL){
                        Redis.RandomSend(message);
                    }else {
                        Redis.publish(ConfigInit.DATACHANNELL,HEAD+message);
                    }
                    logger.info("|||||||"+ConfigInit.REDIS_PUB_MUL+"\t|||||"+ConfigInit.DATACHANNELL+"\t|||||"+message);
                    break;
                default:
                    logger.info("default\t||||"+reqestURI+"\t||||"+message);
                    Class c = Class.forName(PACKAGE);
                    Upper upper = (Upper) c.newInstance();
                    Method[] methods = Upper.class.getMethods();
                    for (int i = 0 ; i < methods.length; i++){
                        if (methods[i].getName().equals(reqestURI)){
                            logger.debug(methods[i]+"|"+message);
                            methods[i].invoke(upper,message);
                        }
                    }
                    logger.info("over default\t||||");
                    break;
            }
        } catch (Exception e){
            logger.error(message+"======"+reqestURI,e);
        }
        finally {
            logger.info("Analyse is over");
            lock.unlock();
        }
    }

    @Override
    public void run() {
        DealSet();
    }
}
