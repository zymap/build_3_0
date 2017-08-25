package com.company.pojo.upper;

import com.company.frame.Config.ConfigInit;
import com.company.util.JsonSplit;
import com.company.util.upper.Factory;
import org.apache.log4j.Logger;

/**
 * Created by zy on 17-8-19.
 */
public class Change {
    private static Logger logger = Logger.getLogger(Change.class);
    public static Machine toMachine(JsonSplit split){
        Machine machine = new Machine();
        machine.setAdn(Factory.cancelQuotation(split.getAdn()));
        machine.setBdevicetype(Factory.cancelQuotation(split.getBdevicetype()));
        machine.setBfloor(Factory.cancelQuotation(split.getBfloor()));
        machine.setBgroup(Factory.cancelQuotation(split.getBgroup()));
        machine.setBorg(Factory.cancelQuotation(split.getBorg()));
        machine.setDevicestatus(Factory.cancelQuotation(split.getDevicestatus()));
        machine.setUuid(Factory.cancelQuotation(split.getUuid()));
        machine.setTem(Factory.cancelQuotation(split.getTem()));
        machine.setMode(Factory.cancelQuotation(split.getModeset()));
        machine.setWind(Factory.cancelQuotation(split.getWind()));
        machine.setRoomTemp(Factory.cancelQuotation(split.getRoomtem()));
        machine.setBus(Factory.cancelQuotation(split.getBus()));
        logger.debug("args:"+split.getAdn()+"|"+split.getBdevicetype()+"|"+split.getBfloor()+"|"
        +split.getBgroup()+"|"+split.getBorg()+"|"+split.getDevicestatus()+"|"+split.getUuid()+"|"
        +split.getTem()+"|"+split.getModeset()+"|"+split.getWind()+"|"+split.getBus());
        return machine;
    }
}
