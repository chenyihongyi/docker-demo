package com.springcloud.demo.dockerdemo.docker_service.thread.communication;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/22 1:30
 */
public class Consumer implements Runnable{

    private Medium medium;

    public Consumer(Medium medium) {
        this.medium = medium;
    }

    @Override
    public void run() {
        while (true) {
            medium.take();
        }
    }
}
