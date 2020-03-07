package com.springcloud.demo.dockerdemo.docker_service.javaSE;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/7 11:25
 */
public class ArrayList扩容Demo01Test {

    public static void main(String[] args) {

        ArrayList扩容Demo01 list = new ArrayList扩容Demo01();

        for(int i = 0; i<12; i++){
            list.add(""+i);
        }

        for(int i = 0; i<list.size(); i++){
            System.out.println(list.get(i));
        }
    }
}
