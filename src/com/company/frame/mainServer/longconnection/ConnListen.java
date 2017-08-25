package com.company.frame.mainServer.longconnection;

import com.company.frame.Config.Config;
import com.company.frame.Config.ConfigInit;
import com.company.util.Redis.JedisPool;
import com.company.util.Redis.Redis;
import com.company.util.ThreadUtil;
import org.apache.log4j.Logger;

/**
 * Created by zy on 17-8-20.
 */
public class ConnListen {
    private static Logger logger = Logger.getLogger(ConnListen.class);

    public static void listen(){
        ThreadUtil.Run(new Runnable() {
            @Override
            public void run() {
                try {
                    new Redis().zsubscribe(new Listener(), ConfigInit.UPPERCHANNEL);
                    JedisPool.getJedis().subscribe(new Listener(),ConfigInit.UPPERCHANNEL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                Redis.subscrib(new Listener(),ConfigInit.UPPERCHANNEL);
//                logger.debug("------------------------ConnListen----------------------------");
            }
        },"ConnListen");

    }
}
