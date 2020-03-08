package com.springcloud.demo.dockerdemo.docker_service.thread;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/8 6:23
 */
public class ThreadDemo2 implements Runnable{

    @Override
    public void run() {
        System.out.println("通过Runnable实现多线程,名称: "+Thread.currentThread().getName());
    }
}
