package com.springcloud.demo.dockerdemo.docker_service.thread;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/21 23:02
 */
public class MyInterruptDemo implements Runnable{

    private static volatile boolean FLAG = true;

    @Override
    public void run() {
        while (FLAG) {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyInterruptDemo());
        thread.start();
        thread.sleep(1000L);
        FLAG = false;
    }
}
