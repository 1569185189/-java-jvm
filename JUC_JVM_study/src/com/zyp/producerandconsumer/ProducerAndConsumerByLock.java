package com.zyp.producerandconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/10 15:02
 */
/**
 * 资源类
 */
class DealValue {
    private int value;
    private final Lock lock = new ReentrantLock();
    /**
     * condition与Lock配合可以实现等待/通知模式
     */
    private final Condition condition = lock.newCondition();
    private final Condition condition2 = lock.newCondition();

    public void add() {
        lock.lock();
        try {
            //if (value > 0) {
            //while判断防止虚假唤醒
            while (value > 0) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            value++;
            condition2.signal();    //精准通知
            System.out.println(Thread.currentThread().getName() + " is over, value = " + value);
        } finally {
            lock.unlock();
        }
    }

    public void desc() {
        lock.lock();
        try {
            //if (value <= 0) {
            //while判断防止虚假唤醒
            while (value <= 0) {
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            value--;
            condition.signal(); //精准通知
            System.out.println(Thread.currentThread().getName() + " is over, value = " + value);
        } finally {
            lock.unlock();
        }
    }
}

public class ProducerAndConsumerByLock {
    public static void main(String[] args) {
        DealValue dealValue = new DealValue();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                dealValue.add();
            }
        }, "producer1").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                dealValue.desc();
            }
        }, "consumer1").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                dealValue.add();
            }
        }, "producer2").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                dealValue.desc();
            }
        }, "consumer2").start();
    }
}
