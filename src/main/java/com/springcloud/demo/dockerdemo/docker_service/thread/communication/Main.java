package com.springcloud.demo.dockerdemo.docker_service.thread.communication;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/22 1:30
 */
public class Main {

    public static void main(String[] args) {
        Medium medium = new Medium();

        new Thread(new Consumer(medium)).start();
        new Thread(new Consumer(medium)).start();
        new Thread(new Consumer(medium)).start();


        new Thread(new Producer(medium)).start();
        new Thread(new Producer(medium)).start();
        new Thread(new Producer(medium)).start();
        new Thread(new Producer(medium)).start();
        new Thread(new Producer(medium)).start();
        new Thread(new Producer(medium)).start();
    }
}
