package com.company.frame.mainServer.transmitter;

import com.company.frame.mainServer.httpserver.HttpOutboundHandler;
import com.company.pojo.Value;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zy on 17-8-16.
 */
public class Swap {
    private static Logger logger = Logger.getLogger(Swap.class);
    private Lock lock;
    private Condition condition;
    private String uuid;
    private String message;

    public Swap(){
        lock = new ReentrantLock();
    }

    public void setMessage(String uuid,String message){
        lock.lock();
        logger.debug("setMessage===========args:"+message);
        this.message = message;
        Value value = SwapHashmap.getValue(uuid);
        value.getCondition().signal();
        lock.unlock();
    }

    public String getMessage(String key){
        lock.lock();
        condition = lock.newCondition();
        String result = "";
        try {
            logger.debug("getMessage await===========");
            Value value = new Value();
            value.setCondition(condition);
            value.setSwap(this);
            value.setTime(System.currentTimeMillis());
            SwapHashmap.add(key,value);
            condition.await();
            result = message;
        } catch (Exception e) {
            logger.debug("getmessage await",e);
        }
        logger.debug("unlock");
        lock.unlock();
        return result;

    }

    private void add(){}



//    private static ConcurrentHashMap<String,String> message = new ConcurrentHashMap<>();
//    private static Lock lock =new ReentrantLock();
//    private static Condition condition = lock.newCondition();
//
//    public static void setMessage(String key,String v){
//        lock.lock();
//        logger.debug("setMessage===========args:"+key+"\t value:"+v);
//        message.put(key,v);
//        condition.signal();
//        lock.unlock();
//    }
//
//    public static String getMessage(String key){
//        lock.lock();
//        String result = "";
//        try {
//            logger.debug("getMessage await===========");
//            condition.await();
//            result = message.get(key);
//        } catch (Exception e) {
//            logger.debug("getmessage await",e);
//        }
//        logger.debug("unlock");
//        lock.unlock();
//        return result;
//
//    }

}
