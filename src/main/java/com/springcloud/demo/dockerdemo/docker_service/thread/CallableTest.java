package com.springcloud.demo.dockerdemo.docker_service.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/8 7:52
 */
public class CallableTest {

    public static void main(String[] args) {
        FutureTask<Object> futureTask = new FutureTask<>(()->{
            System.out.println("通过Callable实现多线程,名称: "+Thread.currentThread().getName());
            return "这是返回值";
        });

/*        MyTask myTask = new MyTask();
        FutureTask<Object> futureTask = new FutureTask<>(myTask);*/

        //FutureTask继承了了Runnable，可以放在Thread中启动执⾏行行
        Thread thread = new Thread(futureTask);
        thread.setName("demo3");
        thread.start();
        System.out.println("主线程名称: "+Thread.currentThread().getName());

        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
