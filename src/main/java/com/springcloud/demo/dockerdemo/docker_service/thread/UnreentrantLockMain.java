package com.springcloud.demo.dockerdemo.docker_service.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/9 6:44
 */
public class UnreentrantLockMain {

 private UnreentrantLock unreentrantLock = new UnreentrantLock();

    //加锁建议在try里面，解锁建议在finally
    public void  methodA(){

        try {
            unreentrantLock.lock();
            System.out.println("methodA方法被调用");
            methodB();

        }catch (InterruptedException e){
            e.fillInStackTrace();

        } finally {
            unreentrantLock.unlock();
        }
    }

    public void methodB(){

        try {
            unreentrantLock.lock();
            System.out.println("methodB方法被调用");
        }catch (InterruptedException e){
            e.fillInStackTrace();
        } finally {
            unreentrantLock.unlock();
        }
    }

    public static void main(String [] args){

        for(int i=0 ;i<10;i++){
            //演示的是同个线程
            new UnreentrantLockMain().methodA();
        }
    }
}
