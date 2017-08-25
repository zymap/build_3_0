package com.company.util.Redis;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zy on 17-8-18.
 */
public class Roll {
    private static String[] IP;
    private static int location = 0;
    private static ReentrantLock lock = new ReentrantLock();

    public static void init(String[] ip){
        IP = ip;
    }

    public static String next(){
        lock.lock();
        String var = "";
        int size = IP.length;
        if (location == size-1){
            var = IP[location];
            location = 0;
            return var;
        }
        var = IP[location];
        location++;
        lock.unlock();
        return var;
    }

}
