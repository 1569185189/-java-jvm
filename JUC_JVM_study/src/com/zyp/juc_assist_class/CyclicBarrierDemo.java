package com.zyp.juc_assist_class;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/11 17:39
 */

/**
 *   CyclicBarrier
 *   的字面意思是可循环（Cyclic）使用的屏障（Barrier）。它要做的事情是，
 *   让一组线程到达一个屏障（也可以叫同步点）时被阻塞，
 *   直到最后一个线程到达屏障时，屏障才会开门，所有
 *   被屏障拦截的线程才会继续干活。
 *   线程进入屏障通过CyclicBarrier的await()方法。
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(6);
        System.out.println("当累计等待的线程数达到6时，所有线程才开始执行");
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("线程数："+Thread.currentThread().getName());
            },Integer.toString(i)).start();
        }
    }
}
