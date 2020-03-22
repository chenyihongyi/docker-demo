package com.springcloud.demo.dockerdemo.docker_service.thread.communication;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/22 1:31
 */
public class Producer implements Runnable{

    private Medium medium;

    public Producer(Medium medium){
        this.medium = medium;
    }

    @Override
    public void run() {
        while (true) {
            medium.put();
        }
    }
}
