package com.company.pojo.upper;


import com.company.frame.Config.ConfigInit;
import com.company.util.JsonSplit;
import com.company.util.Redis.Redis;
import org.apache.log4j.Logger;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by zy on 17-7-21.
 */
public class Upper {
    private static Logger logger = Logger.getLogger(Upper.class);
    public void setStatus(String status) {
        JsonSplit jsonSplit = new JsonSplit(status);
        Machine machine = Change.toMachine(jsonSplit);
        String message = Command.setStatus(machine);
        logger.debug("setStatus:"+message);
        Redis.publish(ConfigInit.UPPERCHANNEL,message);
    }

    public void setTemp(String temp){
        JsonSplit jsonSplit = new JsonSplit(temp);
        Machine machine = Change.toMachine(jsonSplit);
        String message = Command.setTemp(machine);
        logger.debug("setTemp:"+message);
        Redis.publish(ConfigInit.UPPERCHANNEL,message);
    }

    public void setMode(String mode){
        JsonSplit jsonSplit = new JsonSplit(mode);
        Machine machine = Change.toMachine(jsonSplit);
        String message = Command.setMode(machine);
        logger.debug("setMode:"+message);
        Redis.publish(ConfigInit.UPPERCHANNEL,message);
    }

    public void setWind(String wind){
        JsonSplit jsonSplit = new JsonSplit(wind);
        Machine machine = Change.toMachine(jsonSplit);
        String message = Command.setWind(machine);
        logger.debug("setWind:"+message);
        Redis.publish(ConfigInit.UPPERCHANNEL,message);
    }

    public void getStatus(String m){
        JsonSplit jsonSplit = new JsonSplit(m);
        Machine machine = Change.toMachine(jsonSplit);
        String message = Command.getStatus(machine);
        logger.debug("getStatus:"+message);
        Redis.publish(ConfigInit.UPPERCHANNEL,message);
    }

    public void getTemp(String m){
        JsonSplit jsonSplit = new JsonSplit(m);
        Machine machine = Change.toMachine(jsonSplit);
        String message = Command.getTemp(machine);
        logger.debug("getTemp:"+message);
        Redis.publish(ConfigInit.UPPERCHANNEL,message);
    }

    public void getMode(String m){
        JsonSplit jsonSplit = new JsonSplit(m);
        Machine machine = Change.toMachine(jsonSplit);
        String message = Command.getMode(machine);
        logger.debug("getMode:"+message);
        Redis.publish(ConfigInit.UPPERCHANNEL,message);
    }

    public void getWind(String m){
        JsonSplit jsonSplit = new JsonSplit(m);
        Machine machine = Change.toMachine(jsonSplit);
        String message = Command.getWind(machine);
        logger.debug("getWind:"+message);
        Redis.publish(ConfigInit.UPPERCHANNEL,message);
    }

    public void getRoomtem(String m){
        JsonSplit jsonSplit = new JsonSplit(m);
        Machine machine = Change.toMachine(jsonSplit);
        String message = Command.getRoomTemp(machine);
        logger.debug("getRoomtem:"+message);
        Redis.publish(ConfigInit.UPPERCHANNEL,message);
    }
}
