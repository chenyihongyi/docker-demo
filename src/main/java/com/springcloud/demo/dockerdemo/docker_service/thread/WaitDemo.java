package com.springcloud.demo.dockerdemo.docker_service.thread;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/21 21:31
 */
public class WaitDemo implements Runnable{

    private static Object object = new Object();
    private static Object waitObj = new Object();

    @Override
    public void run() {
        //持有资源
        synchronized (waitObj) {
            System.out.println(Thread.currentThread().getName() + "占用资源");
            try {
                waitObj.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "释放资源");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new WaitDemo(), "对比线程");
        thread.start();
        Thread.sleep(1000L);
        synchronized (waitObj){
            waitObj.notify();
        }


    }
}
