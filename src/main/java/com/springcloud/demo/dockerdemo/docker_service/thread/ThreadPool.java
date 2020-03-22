package com.springcloud.demo.dockerdemo.docker_service.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/21 20:02
 */
public class ThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()->{
            System.out.println(Thread.currentThread().getName());
        });
    }
}
