package com.springcloud.demo.dockerdemo.docker_service.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/8 8:15
 */
public class ExecutorServiceTest {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for(int i = 0; i<10; i++){
            executorService.execute(new ThreadDemo4());
        }

        System.out.println("主线程名称: " +Thread.currentThread().getName());
        //关闭线程池
        executorService.shutdown();
    }
}
