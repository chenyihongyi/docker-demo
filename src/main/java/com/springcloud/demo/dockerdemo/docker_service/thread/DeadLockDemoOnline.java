package com.springcloud.demo.dockerdemo.docker_service.thread;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/8 20:31
 */
public class DeadLockDemoOnline {

    private static String locka = "locka";

    private static String lockb = "lockb";

    public void methodA(){

        synchronized (locka){
            System.out.println("我是A方法中获得了锁A "+Thread.currentThread().getName() );

            //让出CPU执行权，不释放锁
            try {
                //Thread.sleep(2000);
                System.out.println("methodA");
            } catch (Exception e) {
                e.printStackTrace();
            }

            synchronized(lockb){
                System.out.println("我是A方法中获得了锁B "+Thread.currentThread().getName() );
            }
        }
    }


    public void methodB(){
        synchronized (lockb){
            System.out.println("我是B方法中获得了锁B "+Thread.currentThread().getName() );

            //让出CPU执行权，不释放锁
            try {
                //Thread.sleep(2000);
                System.out.println("methodB");

            } catch (Exception e) {
                e.printStackTrace();
            }

            synchronized(locka){
                System.out.println("我是B方法中获得了锁A "+Thread.currentThread().getName() );
            }
        }
    }

    public static void main(String [] args){

        System.out.println("主线程运行开始运行："+Thread.currentThread().getName());

        DeadLockDemoOnline deadLockDemo = new DeadLockDemoOnline();

        for(int i=0;i<5;i++){
            new Thread(()->{
                deadLockDemo.methodA();
            }).start();

            new Thread(()->{
                deadLockDemo.methodB();
            }).start();
        }
        System.out.println("主线程运行结束："+Thread.currentThread().getName());
    }
}

