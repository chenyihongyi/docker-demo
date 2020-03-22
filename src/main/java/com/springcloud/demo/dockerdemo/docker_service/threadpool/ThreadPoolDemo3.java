package com.springcloud.demo.dockerdemo.docker_service.threadpool;

import java.util.concurrent.*;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/22 10:07
 */
public class ThreadPoolDemo3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        LinkedBlockingDeque<Runnable> objects = new LinkedBlockingDeque<>();

        for(int i = 0; i<100; i++){
            objects.put(()->{
                System.out.println(Thread.currentThread().getName());
            });
        }

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,20,3L,TimeUnit.MINUTES,objects);
        threadPoolExecutor.prestartAllCoreThreads();
    }
}
