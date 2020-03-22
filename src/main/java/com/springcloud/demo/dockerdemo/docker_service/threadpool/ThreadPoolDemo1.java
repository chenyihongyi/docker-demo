package com.springcloud.demo.dockerdemo.docker_service.threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/22 10:07
 */
public class ThreadPoolDemo1 {

    public static void main(String[] args) {

        LinkedBlockingDeque<Runnable> objects = new LinkedBlockingDeque<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,20,3L,TimeUnit.MINUTES,objects);

        for(int i = 0; i<100; i++){
            threadPoolExecutor.submit(()->{
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
    }
}
