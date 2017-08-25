package com.company.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zy on 17-7-25.
 */
public class ThreadPool {
    private static ExecutorService service = Executors.newCachedThreadPool();

    public void run(Runnable runnable){
        service.execute(runnable);
    }
}
