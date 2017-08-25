package com.company.util.Redis;

import com.company.frame.Config.Config;
import com.company.frame.Config.ConfigInit;
import com.company.frame.mainServer.httpserver.HttpInboundHandler;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import redis.clients.jedis.Jedis;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zy on 17-7-25.
 */
public class JedisPool {
    static {
        PropertyConfigurator.configure("./Logger/log4j.propertites");
    }
    private static Logger logger = Logger.getLogger(HttpInboundHandler.class);
    private static ReentrantLock lock = new ReentrantLock();
    private static ArrayBlockingQueue<Jedis> pool = new ArrayBlockingQueue<Jedis>(30);

    public static void createpool(){
        for (int i = 0; i <30; i++){
            try {
                pool.put(new Jedis(ConfigInit.REDIS_IP, ConfigInit.REDIS_PORT));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static Jedis getJedis() throws InterruptedException {
        logger.debug("JedisPool================getJedis");
        return pool.take();
//        return isAlive(pool.take());
    }

    public static void returnJedis(Jedis jedis){
        try {
            pool.put(jedis);
        } catch (InterruptedException e) {
            logger.debug("JedisPool================returnJedis",e);
        }
    }

//    public static Jedis isalive(Jedis jedis){
////        boolean flag = true;
//        if (jedis.ping().equals("PONE")){
//            return jedis;
//        }
//        return jedis;
//    }

    private static Jedis isAlive(Jedis jedis){
        int time = 0;
        while (true){
            try {
                if (!(jedis.ping().equals("PONG"))){
                    jedis = new Jedis(ConfigInit.REDIS_IP,ConfigInit.REDIS_PORT);
                }
                return jedis;
            }catch (Exception e){
                if (time == 10){
                    System.out.println("start up time out");
                    logger.error("time out:"+time,e);
                    System.exit(1);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                System.out.println("重新链接");
                time++;
                continue;
            }
        }
//        return jedis;
    }
}
