package com.springcloud.demo.dockerdemo.docker_service.thread;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/21 20:00
 */
public class LambdaThread {

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName());
        }).start();
    }
}
