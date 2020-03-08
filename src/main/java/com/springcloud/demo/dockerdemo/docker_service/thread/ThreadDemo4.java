package com.springcloud.demo.dockerdemo.docker_service.thread;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/8 8:12
 */
public class ThreadDemo4  implements Runnable{

    @Override
    public void run() {
        System.out.println("通过线程池+runnable实现多线程, 名称: "+Thread.currentThread().getName());
    }
}
