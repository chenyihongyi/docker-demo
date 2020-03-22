package com.springcloud.demo.dockerdemo.docker_service.thread;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/22 0:31
 */

/**
 * 饿汉式单例
 */
public class HungerSingleton {

    private static HungerSingleton ourInstance = new HungerSingleton();

    public static HungerSingleton getInstance(){
        return ourInstance;
    }

    public HungerSingleton(){

    }

    public static void main(String[] args) {
        for(int i = 0; i <10; i++){
            new Thread(()->{
                System.out.println(HungerSingleton.getInstance());
            }).start();
        }
    }
}
