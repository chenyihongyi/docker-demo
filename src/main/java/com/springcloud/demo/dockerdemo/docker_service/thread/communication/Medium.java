package com.springcloud.demo.dockerdemo.docker_service.thread.communication;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2020/3/22 1:30
 */
public class Medium {

private int num = 0;

private static final int TOTAL = 20;

    /**
     * 接收生产数据
     */
    public synchronized void put(){

        //判断当前的坤村,是否已经式最大的库存容量
        if(num < TOTAL){
        //如果不是,生产完成之后，通知消费者进行性消费
            System.out.println("新增库存------->当前库存"+ ++num);

            notifyAll();
        }else{
            //如果是，则通知生产者进行等待
            try {
                System.out.println("新增库存-------》库存已满"+num);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

}

    /**
     * 获取消费数据
     */
    public synchronized void take() {

        //判断当前库存是否不足
        if (num > 0) {
            //如果充足，在消费完成之后通知生产者进行生产
            System.out.println("消费库存----->当前库存容量" + --num);
            notifyAll();
        }else {
            //如果不足，通知消费者暂停消费
            System.out.println("消费库存---------->库存不足"+num);
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }




    }
}
