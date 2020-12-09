package com.zyp.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/12/5 17:09
 */
class TestOne{
    public synchronized void test(){
        System.out.println("wait");
        try {
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class TestThreadMethod {
    public static void main(String[] args) {

        TestOne testOne = new TestOne();

        Thread thread = new Thread(() -> {
            //testOne.test();
            System.out.println("test interrupted");
            System.out.println("123");
        });
        thread.start();
        //中断thread线程，设置中断标志位
        thread.interrupt();
        //检测thread线程，是否被中断，不清除中断标志位
        System.out.println(thread.isInterrupted());

        Thread.currentThread().interrupt();
        //中断当前线程,main线程，清除中断标志位，源码见
        System.out.println(thread.interrupted());
    }
}
