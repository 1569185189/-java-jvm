package com.zyp.producerandconsumer;

import java.util.concurrent.TimeUnit;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/10 14:23
 */
//资源类
class DealNum {
    private int value;

    public synchronized void add() {
        //if (value > 0) {
        while (value > 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        value++;
        this.notifyAll();
        System.out.println(Thread.currentThread().getName() + " is over" + " , value = " + value);
    }

    public synchronized void desc() {
        //if (value <= 0) {
        while (value <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        value--;
        this.notifyAll();
        System.out.println(Thread.currentThread().getName() + " is over" + " , value = " + value);
    }
}

public class ProducerAndConsumerBySync {
    public static void main(String[] args) throws InterruptedException {
        DealNum dealNum = new DealNum();
        //两条线程使用if来判断不会出现value等于其他值(非0,1)的情况，因为只有一个加的线程和一个减的线程
        /*new Thread(()->{
            for (int i = 0; i < 10; i++) {
                dealNum.add();
            }
        },"producer").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                dealNum.desc();
            }
        },"consumer").start();*/

        //多条线程应该使用while来进行循环判断，防止虚假唤醒线程。
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                dealNum.add();
            }
        }, "producer1").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                dealNum.add();
            }
        }, "producer2").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                dealNum.desc();
            }
        }, "consumer1").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                dealNum.desc();
            }
        }, "consumer2").start();
    }
}
