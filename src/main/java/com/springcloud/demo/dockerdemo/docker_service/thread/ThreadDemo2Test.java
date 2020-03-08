package com.springcloud.demo.dockerdemo.docker_service.thread;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/8 6:25
 */
public class ThreadDemo2Test {

    public static void main(String[] args) {

/*        ThreadDemo2 threadDemo2 = new ThreadDemo2();
        Thread thread = new Thread(threadDemo2);
        thread.setName("demo02");
        thread.start();
        System.out.println("主线程名称: "+Thread.currentThread().getName());*/

        Thread thread = new Thread(() -> {
            System.out.println("通过Runnable实现多线程,名称: " + Thread.currentThread().getName());
        });
        thread.setName("demo2");
        thread.start();
        System.out.println("主线程名称: " + Thread.currentThread().getName());

    }
}
