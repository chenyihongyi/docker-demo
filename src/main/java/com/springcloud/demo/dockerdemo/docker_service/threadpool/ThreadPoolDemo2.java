package com.springcloud.demo.dockerdemo.docker_service.threadpool;

import java.util.concurrent.*;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/22 10:07
 */
public class ThreadPoolDemo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        LinkedBlockingDeque<Runnable> objects = new LinkedBlockingDeque<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,20,3L,TimeUnit.MINUTES,objects);
        Future<String> submit = null;
        for(int i = 0; i<100; i++){

           submit = threadPoolExecutor.submit(new CallableDemo());
        }

        for(int i = 0; i<100; i++){
            System.out.println(submit.get());
        }
    }
}
