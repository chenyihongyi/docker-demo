package com.springcloud.demo.dockerdemo.docker_service.thread.communication;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/22 1:07
 */
public class CommunicationDemo02 {

    private static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        new Thread(()->{
            while (!flag) {
                synchronized (obj){
                    try {
                        System.out.println("flag is false!");
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
            System.out.println("flag is true!");
        }).start();
        Thread.sleep(1000L);

        new Thread(() -> {
            flag = true;
            synchronized (obj) {
                obj.notify();
            }
        }).start();
    }
}
