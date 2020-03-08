package com.springcloud.demo.dockerdemo.docker_service.thread;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/8 6:14
 */
public class ThreadDemo01Test {

    public static void main(String[] args) {
        ThreadDemo01 threadDemo01 =new ThreadDemo01();
        threadDemo01.setName("demo01");

        threadDemo01.start();
        System.out.println("主线程名称: "+Thread.currentThread().getName());
    }
}
