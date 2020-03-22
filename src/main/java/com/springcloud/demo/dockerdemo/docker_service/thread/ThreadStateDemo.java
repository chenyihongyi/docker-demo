package com.springcloud.demo.dockerdemo.docker_service.thread;

import java.io.IOException;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/21 19:10
 */
public class ThreadStateDemo {

    public static void main(String[] args) throws InterruptedException {
/*
        //RUNNABLE
        Thread thread = new Thread(()->{
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();*/

//BLOCKED
        Object obj = new Object();
        Thread thread = new Thread(() -> {
            synchronized (obj) {
                try {
                    Thread.sleep(100000000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        Thread.sleep(2000L);
        Thread thread2 = new Thread(() -> {
            synchronized (obj) {

            }
        });
        thread2.start();
    }
}
