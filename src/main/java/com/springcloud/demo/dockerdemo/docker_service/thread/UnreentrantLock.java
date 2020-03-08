package com.springcloud.demo.dockerdemo.docker_service.thread;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/9 6:32
 */
public class UnreentrantLock {

    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException{

        System.out.println("进入wait等待 " + Thread.currentThread().getName());

        //判断是否已经被锁，如果被锁则当前请求的线程进行等待
        while (isLocked){
            System.out.println("进入wait等待 " + Thread.currentThread().getName());
            wait();
        }
        //进行加锁
        isLocked = true ;
    }

    public synchronized void unlock(){
        System.out.println("进入unlock解锁 " +Thread.currentThread().getName());
        isLocked = false;
        //唤醒对象锁池里面的一个线程
        notify();
    }
}
