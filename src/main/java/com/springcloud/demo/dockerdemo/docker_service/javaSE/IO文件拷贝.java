package com.springcloud.demo.dockerdemo.docker_service.javaSE;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/6 7:20
 */
public class IO文件拷贝 {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        try (
                FileInputStream fis = new FileInputStream("C:\\Users\\ElvisChen\\Desktop\\test.txt");
                BufferedInputStream bis = new BufferedInputStream(fis);
                FileOutputStream fos = new FileOutputStream("C:\\Users\\ElvisChen\\Desktop\\copy.txt");
                BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            int size;
            byte[] buf = new byte[1024];
            while((size = bis.read(buf)) != -1){
                bos.write(buf,0, size);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


