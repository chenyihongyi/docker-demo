package com.springcloud.demo.dockerdemo.docker_service.thread;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/21 22:38
 */
public class InterruptDemo implements Runnable{

    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName());

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new InterruptDemo());
        thread.start();
        Thread.sleep(2000L);
        thread.stop();
    }
}
