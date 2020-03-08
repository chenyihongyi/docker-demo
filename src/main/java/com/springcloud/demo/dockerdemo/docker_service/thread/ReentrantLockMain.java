package com.springcloud.demo.dockerdemo.docker_service.thread;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/9 7:29
 */
public class ReentrantLockMain {

    private ReentrantLock reentrantLock = new ReentrantLock();

    //加锁建议在try⾥里里⾯面，解锁建议在finally
    public void methodA(){
        try {
            reentrantLock.lock();
            System.out.println("methodA方法被调用");
            methodB();

        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }

    public void methodB() {

        try {
            reentrantLock.lock();
            System.out.println("methodB方法被调用");
        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i<10; i++){
            new ReentrantLockMain().methodA();
        }
    }
}
