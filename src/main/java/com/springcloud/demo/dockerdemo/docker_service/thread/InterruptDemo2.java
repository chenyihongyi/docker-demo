package com.springcloud.demo.dockerdemo.docker_service.thread;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/21 22:54
 */
public class InterruptDemo2 implements Runnable{

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new InterruptDemo2());
        thread.start();
        Thread.sleep(1000L);
        thread.interrupt();
    }
}
