package com.springcloud.demo.dockerdemo.docker_service.位运算;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/6 6:09
 */
public class 两个变量交换 {
    public static void main(String[] args) {
       // swap(31,40);
        swap2(31,40);
    }

/*    public static void swap(int a, int b){
        System.out.printf("a=%d, b=%d", a,b);
        a = a + b ;
        b = a - b ;
        a = a - b ;
        System.out.printf("\na=%d, b=%d", a, b);
    }*/

    public static void swap2(int a, int b){
        System.out.printf("a=%d, b=%d", a,b);
        a = a ^ b ;
        b = b ^ a ;
        a = a ^ b ;
        System.out.printf("\na=%d, b=%d", a, b);
    }
}
