package com.springcloud.demo.dockerdemo.docker_service.thread;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/8 5:55
 */
public class ThreadDemo01 extends Thread{

    @Override
    public void run() {
        System.out.println("继承Thread实现多线程, 名称: "+Thread.currentThread().getName());
    }

}
