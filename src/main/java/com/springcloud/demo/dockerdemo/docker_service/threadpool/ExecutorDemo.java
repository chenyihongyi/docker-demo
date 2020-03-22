package com.springcloud.demo.dockerdemo.docker_service.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/22 12:06
 */
public class ExecutorDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService1 = Executors.newFixedThreadPool(2);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        ExecutorService executorService2 = Executors.newWorkStealingPool();
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();
        ScheduledExecutorService scheduledExecutorService1 = Executors.newSingleThreadScheduledExecutor();

        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName());
        });
    }
}
