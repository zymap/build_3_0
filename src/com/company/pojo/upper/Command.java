package com.company.pojo.upper;

import com.sun.org.apache.regexp.internal.RE;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zy on 17-7-30.
 */
public class Command{
    private static String LOCALIP="100001";
    private static String BEGIN = "#";
    private static String READ = "11";
    private static String NOSET = "ff";
    private static String GET_STATUS = "#01=";
    private static String SET_STATUS = "#101=";
    private static String GET_MODE = "#02=";
    private static String SET_MODE = "#102=";
    private static String SET_TEM = "#103=";
    private static String GET_TEM = "#03=";
    private static String SET_WIND = "#104=";
    private static String GET_WIND = "#04=";
    private static String SET_IP = "#05="+LOCALIP;
    private static String GET_ROOMTEM = "#06=";
    private static String BORG = "borg=";
    private static String UUID = "#uuid=";
    private static Logger logger = Logger.getLogger(Command.class);

    private static String addZero(String var){
        if (var.length()==1) {
            return "00" + var;
        }
        return "0"+var;
    }

    public static String setStatus(Machine machine){
        String borg = machine.getBorg();
        String status = machine.getDevicestatus();
        String adn = machine.getAdn();
        String bfloor = machine.getBfloor();
        String bus = machine.getBus();
        String uuid = machine.getUuid();
        String result = BORG+borg+"/"+BEGIN+UUID+uuid+GET_STATUS+NOSET+SET_STATUS+status+GET_MODE+NOSET+SET_MODE+NOSET+GET_TEM+NOSET+SET_TEM+NOSET+SET_WIND+NOSET+GET_WIND+NOSET+SET_IP+addZero(bfloor)+addZero(bus)+addZero(adn)+GET_ROOMTEM+NOSET;
        logger.debug(result);
        return result;
    }

    public static String setTemp(Machine machine){
        String borg = machine.getBorg();
        String temp = machine.getTem();
        String uuid = machine.getUuid();
        String adn = machine.getAdn();
        String bfloor = machine.getBfloor();
        String bus = machine.getBus();
        String result = BORG+borg+"/"+BEGIN+UUID+uuid+GET_STATUS+NOSET+SET_STATUS+NOSET+GET_MODE+NOSET+SET_MODE+NOSET+GET_TEM+NOSET+SET_TEM+temp+SET_WIND+NOSET+GET_WIND+NOSET+SET_IP+addZero(bfloor)+addZero(bus)+addZero(adn)+GET_ROOMTEM+NOSET;
        logger.debug(result);
        return result;
    }

    public static String setMode(Machine machine){
        String borg = machine.getBorg();
        String mode = machine.getMode();
        String uuid = machine.getUuid();
        String adn = machine.getAdn();
        String bfloor = machine.getBfloor();
        String bus = machine.getBus();
        String result = BORG+borg+"/"+BEGIN+UUID+uuid+GET_STATUS+NOSET+SET_STATUS+NOSET+GET_MODE+NOSET+SET_MODE+mode+GET_TEM+NOSET+SET_TEM+NOSET+SET_WIND+NOSET+GET_WIND+NOSET+SET_IP+addZero(bfloor)+addZero(bus)+addZero(adn)+GET_ROOMTEM+NOSET;
        logger.debug(result);
        return result;
    }

    public static String setWind(Machine machine){
        String borg = machine.getBorg();
        String wind = machine.getWind();
        String uuid = machine.getUuid();
        String adn = machine.getAdn();
        String bfloor = machine.getBfloor();
        String bus = machine.getBus();
        String result = BORG+borg+"/"+BEGIN+UUID+uuid+GET_STATUS+NOSET+SET_STATUS+NOSET+GET_MODE+NOSET+SET_MODE+NOSET+GET_TEM+NOSET+SET_TEM+NOSET+SET_WIND+wind+GET_WIND+NOSET+SET_IP+addZero(bfloor)+addZero(bus)+addZero(adn)+GET_ROOMTEM+NOSET;
        logger.debug(result);
        return result;
    }

    public static String getStatus(Machine machine){
        String borg = machine.getBorg();
        String uuid = machine.getUuid();
        String adn = machine.getAdn();
        String bfloor = machine.getBfloor();
        String bus = machine.getBus();
        String result = BORG+borg+"/"+BEGIN+UUID+uuid+GET_STATUS+READ+SET_STATUS+NOSET+GET_MODE+NOSET+SET_MODE+NOSET+GET_TEM+NOSET+SET_TEM+NOSET+SET_WIND+NOSET+GET_WIND+NOSET+SET_IP+addZero(bfloor)+addZero(bus)+addZero(adn)+GET_ROOMTEM+NOSET;
        logger.debug(result);
        return result;
    }

    public static String getTemp(Machine machine){
        String borg = machine.getBorg();
        String uuid = machine.getUuid();
        String adn = machine.getAdn();
        String bfloor = machine.getBfloor();
        String bus = machine.getBus();
        String result = BORG+borg+"/"+BEGIN+UUID+uuid+GET_STATUS+NOSET+SET_STATUS+NOSET+GET_MODE+NOSET+SET_MODE+NOSET+GET_TEM+READ+SET_TEM+NOSET+SET_WIND+NOSET+GET_WIND+NOSET+SET_IP+addZero(bfloor)+addZero(bus)+addZero(adn)+GET_ROOMTEM+NOSET;
        logger.debug(result);
        return result;
    }

    public static String getMode(Machine machine){
        String borg = machine.getBorg();
        String uuid = machine.getUuid();
        String adn = machine.getAdn();
        String bfloor = machine.getBfloor();
        String bus = machine.getBus();
        String result = BORG+borg+"/"+BEGIN+UUID+uuid+GET_STATUS+NOSET+SET_STATUS+NOSET+GET_MODE+READ+SET_MODE+NOSET+GET_TEM+NOSET+SET_TEM+NOSET+SET_WIND+NOSET+GET_WIND+NOSET+SET_IP+addZero(bfloor)+addZero(bus)+addZero(adn)+GET_ROOMTEM+NOSET;
        return result;
    }

    public static String getWind(Machine machine){
        String borg = machine.getBorg();
        String uuid = machine.getUuid();
        String adn = machine.getAdn();
        String bfloor = machine.getBfloor();
        String bus = machine.getBus();
        String result = BORG+borg+"/"+BEGIN+UUID+uuid+GET_STATUS+NOSET+SET_STATUS+NOSET+GET_MODE+NOSET+SET_MODE+NOSET+GET_TEM+NOSET+SET_TEM+NOSET+SET_WIND+NOSET+GET_WIND+READ+SET_IP+addZero(bfloor)+addZero(bus)+addZero(adn)+GET_ROOMTEM+NOSET;
        logger.debug(result);
        return result;
    }

    public static String getRoomTemp(Machine machine){
        String borg = machine.getBorg();
        String uuid = machine.getUuid();
        String adn = machine.getAdn();
        String bfloor = machine.getBfloor();
        String bus = machine.getBus();
        String result = BORG+borg+"/"+BEGIN+UUID+uuid+GET_STATUS+NOSET+SET_STATUS+NOSET+GET_MODE+NOSET+SET_MODE+NOSET+GET_TEM+NOSET+SET_TEM+NOSET+SET_WIND+NOSET+GET_WIND+NOSET+SET_IP+addZero(bfloor)+addZero(bus)+addZero(adn)+GET_ROOMTEM+READ;
        logger.debug(result);
        return result;
    }

}
