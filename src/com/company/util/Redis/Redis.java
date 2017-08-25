package com.company.util.Redis;

import com.company.frame.Config.Config;
import com.company.frame.Config.ConfigInit;
import com.company.util.ThreadPool;
import com.company.util.ThreadUtil;
import com.sun.org.apache.regexp.internal.RE;
import io.netty.util.internal.ConcurrentSet;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zy on 17-7-17.
 */


public class Redis {
    private static Logger logger = Logger.getLogger(Redis.class);
    private static ReentrantLock lock = new ReentrantLock();

    public static void publish(String channel, String message){
        Jedis jedis = null;
        try {
            jedis = JedisPool.getJedis();
            jedis.publish(channel,message);
        } catch (InterruptedException e) {
           logger.debug("Redis-----publish",e);
        } finally {
            JedisPool.returnJedis(jedis);
        }
    }

    public static void subscrib(JedisPubSub sub,String channel){
        Jedis jedis = null;
        try {
            jedis = JedisPool.getJedis();
            jedis.subscribe(sub,channel);
        } catch (InterruptedException e) {
            logger.debug("Redis-----subscrib",e);
            JedisPool.returnJedis(jedis);
        }
//        finally {
//        }
    }

    public static void put(String key, String v)
    {
        Jedis jedis = null;
        try {
            jedis = JedisPool.getJedis();
            jedis.set(key,v);
        } catch (InterruptedException e) {
            logger.debug("Redis-----put",e);
        } finally {
            JedisPool.returnJedis(jedis);
        }
    }

    public static String get(String key) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = JedisPool.getJedis();
            result = jedis.get(key);
        } catch (InterruptedException e) {
            logger.debug("Redis-----get",e);
        } finally {
            JedisPool.returnJedis(jedis);
        }
        return result;
    }

    public static void setKeyTTL(String key, int time ){
        Jedis jedis = null;
        try {
            jedis = JedisPool.getJedis();
            jedis.expire(key,time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            JedisPool.returnJedis(jedis);
        }
    }

    public static Set<String> getKeys(){
        lock.lock();
        Jedis jedis = null;
        Set<String> set = null;
        try {
            jedis = JedisPool.getJedis();
            set =jedis.keys("*");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            JedisPool.returnJedis(jedis);
            lock.unlock();
        }
        return set;
    }

    public static long getLength(String key){
        lock.lock();
        int i = 0;
        Jedis jedis = null;
        try {
            jedis = JedisPool.getJedis();
            i = Math.toIntExact(jedis.llen(key));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            JedisPool.returnJedis(jedis);
            lock.unlock();
        }
        return i;
    }

    public static List<String> getList(String key,long start,long end){
        lock.lock();
        List<String> list = null;
        Jedis jedis = null;
        try {
            jedis = JedisPool.getJedis();
            list = jedis.lrange(key,start,end);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            JedisPool.returnJedis(jedis);
            lock.unlock();
        }
        return list;
    }

//    public static void Lsubscrib(String channel,JedisPubSub sub){
//        ThreadUtil.Run(new Runnable() {
//            @Override
//            public void run() {
//                subscrib(sub,channel);
//            }
//        },"Listen Subcribe");
//        ThreadUtil.Run(new Runnable() {
//            @Override
//            public void run() {
//                Heartbeat heartbeat = new Heartbeat();
//                heartbeat.Heartbeat(channel,sub);
//            }
//        },"listener");
//    }

    public static void RandomSend(String message){
        publish(Roll.next(),message);
    }

    public void zsubscribe(JedisPubSub sub, String channel){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Jedis jedis = new Jedis(ConfigInit.REDIS_IP, ConfigInit.REDIS_PORT);
                try {
                    jedis.subscribe(sub,channel);
                }catch (Exception e){
                    jedis.disconnect();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                HeartBeat heartBeat = new HeartBeat();
                try {
                    heartBeat.Heartbeat(channel,sub);
                } catch (InterruptedException e) {
                    logger.error("heartbeat is broken.....");
                }
            }
        }).start();
    }
}
