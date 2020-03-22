package com.springcloud.demo.dockerdemo.docker_service.thread;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/21 19:50
 */
public class MyThread extends Thread{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.setName("elvis, go for it");
        myThread.start();
    }
}
