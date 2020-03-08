package com.springcloud.demo.dockerdemo.docker_service.thread;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/9 7:16
 */

/**
 * 可重入锁
 */
public class ReentrantLock {

    private boolean isLocked = false;

    //用于记录是不是重入的线程
    private Thread lockedOwer = null;

    //累计加锁次数，加锁一次累加1，解锁一次减少1
    private int lockedCount = 0;

    public synchronized void lock() throws InterruptedException{
        System.out.println("进入lock加锁 " +Thread.currentThread().getName());
        Thread thread = Thread.currentThread();

        //判断是否是同个线程获取锁, 引用地址的比较
        while(isLocked && lockedOwer !=thread){
            System.out.println("进入wait等待 " + Thread.currentThread().getName());
            System.out.println("当前锁状态 isLocked = " +isLocked);
            System.out.println("当前count数量 lockedCount = " +lockedCount);
            wait();
        }

        //进行加锁
        isLocked = true ;
        lockedOwer = thread ;
        lockedCount++;
    }

    public synchronized void unlock(){
        System.out.println("进入unlock解锁 " +Thread.currentThread().getName());

        Thread thread = Thread.currentThread();

        //线程A加的锁，只能由线程A解锁，其他线程B不能解锁
        if(thread == this.lockedOwer){
            lockedCount--;
            if(lockedCount == 0){
                isLocked = false;
                lockedOwer = null;
                //唤醒对象锁池里面的一个线程
                notify();
            }
        }
    }
}
