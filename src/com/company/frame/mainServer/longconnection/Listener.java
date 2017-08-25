package com.company.frame.mainServer.longconnection;

import com.company.util.Redis.HeartBeat;
import com.company.util.Redis.RedisMessage;
import com.company.util.ThreadUtil;
import org.apache.log4j.Logger;
import redis.clients.jedis.JedisPubSub;


/**
 * Created by zy on 17-7-25.
 */
public class Listener extends JedisPubSub{
    private final String HEARTBEAT = "HEARTBEAT";
    private final String MESSAGE = "MESSAGE";
    @Override
    public void onMessage(String channel, String message) {
        ThreadUtil.Run(new Runnable() {
            @Override
            public void run() {
                switch (RedisMessage.getHead(message)){
                    case HEARTBEAT:
                        HeartBeat.HEARTBEAT = RedisMessage.getBody(message);
                        new HeartBeat().countDown();
                        break;
                    case MESSAGE:
                        System.out.println(RedisMessage.getBody(message));
                        break;
                }
            }
        },"Listener JedisPubSub");

    }
//    private Logger logger = Logger.getLogger(Listener.class);
//    public void onMessage(String channel, final String message) {
//        ThreadUtil.Run(new Runnable() {
//            @Override
//            public void run() {
//                String[] temp = message.split("/");
//                String[] var = temp[0].split("=");
//                String borg = var[1];
//                new LongConnServer().sendMessage(borg,temp[1]);
//                logger.debug("channel:"+channel+"=====message:"+message+"=========borg:"+borg+"\ttemp[1]:"+temp[1]);
//            }
//        },"Listener JedisPubSub");
//    }
}
