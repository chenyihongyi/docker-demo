package com.springcloud.demo.dockerdemo.docker_service.thread.communication02;

import java.io.*;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/22 2:15
 */
public class Main {

    public static void main(String[] args) throws IOException {
        PipedInputStream pipedInputStream = new PipedInputStream();
        PipedOutputStream pipedOutputStream = new PipedOutputStream();

        pipedOutputStream.connect(pipedInputStream);

        new Thread(new Reader(pipedInputStream)).start();
         BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            pipedOutputStream.write(bufferedReader.readLine().getBytes());
        } finally {
            pipedOutputStream.close();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }
}
