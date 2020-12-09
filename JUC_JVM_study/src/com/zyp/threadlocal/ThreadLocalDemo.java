package com.zyp.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/13 15:02
 */
public class ThreadLocalDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        //初始值
        threadLocal.set("initialize ThreadLocal");
        System.out.println(Thread.currentThread().getName() + " get the value " + threadLocal.get());
        new Thread(() -> {
            threadLocal.set("change ThreadLocal");
            System.out.println(Thread.currentThread().getName() + " get the value " + threadLocal.get());
        }, "AAA").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " get the value " + threadLocal.get());
        }, "BBB").start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("==============================================");
        System.out.println(Thread.currentThread().getName() + " get the value " + threadLocal.get());
        threadLocal.set("change in the " + Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + " get the value " + threadLocal.get());
        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " get the value " + threadLocal.get());
        }, "CCC").start();
    }
}
