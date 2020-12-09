package com.zyp.lock_support;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/13 14:22
 */

/**
 * LockSupport同步线程和wait/notify不一样，LockSupport并不需要获取对象的监视器，
 * 而是给线程一个“许可”(permit)。而permit只能是0个或者1个。
 * unpark会给线程一个permit，而且最多是1；
 * 而park会消耗一个permit并返回，如果线程没有permit则会阻塞
 */
public class LockSupportDemo {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " invoke park()");
        }, "AAA");
        Thread thread2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // unpark两次但是，permit不会叠加
            LockSupport.unpark(thread1);
            LockSupport.unpark(thread1);
            System.out.println(Thread.currentThread().getName() + " invoke park()");
        }, "BBB");
        thread1.start();
        thread2.start();
    }
}
