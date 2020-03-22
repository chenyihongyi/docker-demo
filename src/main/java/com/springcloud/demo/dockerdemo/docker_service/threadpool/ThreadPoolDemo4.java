package com.springcloud.demo.dockerdemo.docker_service.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/22 10:07
 */

/**
 * 线程池满了，自定义拒绝策略
 */
public class ThreadPoolDemo4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        LinkedBlockingDeque<Runnable> objects = new LinkedBlockingDeque<>(20);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,20,3L,TimeUnit.MINUTES,objects,new CustomPolicy());
        for (int i = 0; i < 50; i++) {
            threadPoolExecutor.submit(() -> {
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadPoolExecutor.getActiveCount());
            });
        }
    }
}
