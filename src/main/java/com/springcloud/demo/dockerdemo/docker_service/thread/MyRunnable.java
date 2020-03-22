package com.springcloud.demo.dockerdemo.docker_service.thread;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/21 19:47
 */
public class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());

    }

    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.setName("elvis");
        thread.start();
    }
}
