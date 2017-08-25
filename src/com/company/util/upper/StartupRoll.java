package com.company.util.upper;

import com.company.frame.mainServer.longconnection.ChannelMap;
import com.company.frame.mainServer.longconnection.LongConnServer;
import com.company.util.Redis.Redis;
import com.company.util.upper.Factory;
import org.apache.log4j.Logger;

import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by zy on 17-8-18.
 */
public class StartupRoll implements Runnable{

    private static Logger logger = Logger.getLogger(StartupRoll.class);
//    private LinkedBlockingDeque<String> list = new LinkedBlockingDeque();
    private List<String> list;

    public void setBorg(String borg) {
        this.borg = borg;
    }

    private String borg;

    public void setList(List list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (String s:list){
            new LongConnServer().sendMessage(borg,Factory.upperRollData(s));
            logger.debug(s);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.debug(s,e);
            }
        }
        new LongConnServer().sendMessage(borg,Factory.END);
        logger.debug(borg+Factory.END);
    }
}
