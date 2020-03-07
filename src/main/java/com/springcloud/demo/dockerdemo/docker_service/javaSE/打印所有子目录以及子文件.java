package com.springcloud.demo.dockerdemo.docker_service.javaSE;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/6 8:51
 */
public class 打印所有子目录以及子文件 {

    public static void main(String[] args) {
        //找出某⽬目录下的所有⼦子⽬目录以及⼦子⽂文件并打印到控制台上
        List<String> paths = new ArrayList<>();

        getAllFilePaths(new File("E:\\java_api"),paths);

        for (String path : paths) {
            System.out.println(path);
        }
    }

    private static void getAllFilePaths(File filePath,List<String> paths) {
        File[] files = filePath.listFiles();
        if (files == null) {
            return;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                paths.add(f.getPath());
                getAllFilePaths(f,paths);
            } else {
                paths.add(f.getPath());
            }
        }
    }
}
