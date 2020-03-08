package com.springcloud.demo.dockerdemo.docker_service.thread;

import java.util.concurrent.Callable;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/8 7:49
 */
public class MyTask implements Callable<Object> {

    @Override
    public Object call() throws Exception {
        System.out.println("通过Callable实现多线程,名称: "+Thread.currentThread().getName());
        return "这是返回值";
    }
}
