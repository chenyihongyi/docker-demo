package com.springcloud.demo.dockerdemo.docker_service.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/22 11:41
 */
public class CustomPolicy implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r,ThreadPoolExecutor executor) {
        //发送邮件告警
        System.out.println("线程池满了");
    }
}
