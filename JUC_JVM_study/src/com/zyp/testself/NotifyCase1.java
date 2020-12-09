package com.zyp.testself;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/11 15:31
 */
//资源类
class Print {
    private final Lock lock = new ReentrantLock();
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private final Condition condition3 = lock.newCondition();

    //判断标志,1->A, 2->B, 3->C
    private int flag = 1;

    public void printA() {
        lock.lock();
        try {
            //多线程下判断用while，防止虚假唤醒
            while (flag != 1) {
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " print A");
            //唤醒打印B的线程
            condition2.signal();
            //修改标志的值
            flag = 2;
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            //多线程下判断用while，防止虚假唤醒
            while (flag != 2) {
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " print B");
            //唤醒打印C的线程
            condition3.signal();
            //修改标志的值
            flag = 3;
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            //多线程下判断用while，防止虚假唤醒
            while (flag != 3) {
                try {
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " print C");
            //唤醒打印A的线程
            condition1.signal();
            //修改标志的值
            flag = 1;
        } finally {
            lock.unlock();
        }
    }
}

public class NotifyCase1 {
    public static void main(String[] args) {
        Print print = new Print();
        for (int i = 0; i < 10; i++) {
            new Thread(print::printA, "AAA").start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(print::printB, "BBB").start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(print::printC, "CCC").start();
        }
    }
}
