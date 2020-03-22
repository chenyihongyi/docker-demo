package com.springcloud.demo.dockerdemo.docker_service.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/21 16:12
 */
public class UnSafeThread {

    private static int num = 0;


    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    /**
     * 每次调用对num进行++操作
     */
    public static void inCreate(){
        num ++;
    }

    public static void main(String[] args) {
        for(int i = 0; i <10; i++){
            new Thread(()->{
                for(int j = 0; j<100;j++){
                    inCreate();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //每个线程执行完成之后，调用countdownLatch
                countDownLatch.countDown();
            }).start();
        }
        while (true){
            if(countDownLatch.getCount() == 0){
                System.out.println(num);
                break;
            }
        }
    }
}
