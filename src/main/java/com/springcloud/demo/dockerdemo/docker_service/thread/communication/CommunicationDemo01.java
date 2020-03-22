package com.springcloud.demo.dockerdemo.docker_service.thread.communication;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/22 1:07
 */
public class CommunicationDemo01 {

    private static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (!flag) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("flag is false!");
            }
            System.out.println("flag is true!");
        }).start();
        Thread.sleep(1000L);

        new Thread(()->{
            flag = true;
        }).start();
    }
}
