package com.zyp.juc_assist_class;

import java.util.concurrent.CountDownLatch;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/11 16:26
 */

/**
 *  CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，这些线程会阻塞。
 *  其它线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞)，
 *  当计数器的值变为0时，因await方法阻塞的线程会被唤醒，继续执行。
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        //所有人离开才能锁门
        System.out.println("所有人离开才能锁门");
        for (int i = 0; i < 6; i++) {
            //共匿名内部类访问的变量需要使用final修饰
            final int temp = i + 1;
            new Thread(() -> {
                System.out.println("NO." + temp + "  student leave the room");
                //离开一个人count数减一，放在线程中才有效(**线程**倒计时器)
                countDownLatch.countDown();
                //获取当前计数器的值
                System.out.println(countDownLatch.getCount());
            }).start();
        }
        //计数器只减一
        /*countDownLatch.countDown();
        System.out.println(countDownLatch.getCount());*/
        countDownLatch.await();
        System.out.println("close the door");
    }
}
