package com.zyp.testself;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/11 15:50
 */
class Print2 {
    //判断标志位
    private int flag = 1;

    public synchronized void printA() {
        //多线程情况下判断用while，防止虚假唤醒
        while (flag != 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //修改标志位，唤醒打印B的线程
        flag = 2;
        //唤醒等待的线程
        this.notifyAll();
        System.out.println("Thread " + Thread.currentThread().getName() + " print A");
    }

    public synchronized void printB() {
        //多线程情况下判断用while，防止虚假唤醒
        while (flag != 2) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //修改标志位，唤醒打印C的线程
        flag = 3;
        //唤醒等待的线程
        this.notifyAll();
        System.out.println("Thread " + Thread.currentThread().getName() + " print B");
    }

    public synchronized void printC() {
        //多线程情况下判断用while，防止虚假唤醒
        while (flag != 3) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //修改标志位，唤醒打印A的线程
        flag = 1;
        //唤醒等待的线程
        this.notifyAll();
        System.out.println("Thread " + Thread.currentThread().getName() + " print C");
    }
}

public class NotifyCase2 {
    public static void main(String[] args) {
        Print2 print2 = new Print2();
        for (int i = 0; i < 10; i++) {
            new Thread(print2::printA, "AAA").start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(print2::printB, "BBB").start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(print2::printC, "CCC").start();
        }
    }
}
