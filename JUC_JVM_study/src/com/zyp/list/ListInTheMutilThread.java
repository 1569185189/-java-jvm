package com.zyp.list;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/10 9:45
 */
//线程不安全
public class ListInTheMutilThread {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add(i);
            }
            System.out.println(Thread.currentThread().getName() + " , " + list.size());
        }, "A");
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add(i + 9);
            }
            System.out.println(Thread.currentThread().getName() + " , " + list.size());
        }, "B");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        list.forEach((i) -> {
            System.out.println(Thread.currentThread().getName() + " , " + i);
        });
    }
}
