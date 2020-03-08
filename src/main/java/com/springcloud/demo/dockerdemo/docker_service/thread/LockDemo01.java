package com.springcloud.demo.dockerdemo.docker_service.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/8 14:12
 */
public class LockDemo01 {

    private int num = 0 ;

    //使用lock，每个对象都是有锁，只有获得这个锁才可以进行对应的操作
    Lock lock = new ReentrantLock();

    public void add1(){
        lock.lock();
        try{
            num++;
        }finally {
            lock.unlock();
        }
    }


    //使用synchronized，和上述是一个操作，这个是保证方法被锁住而已，上述的是代码块被锁住
    public synchronized void add2(){
        num ++;
    }
}
