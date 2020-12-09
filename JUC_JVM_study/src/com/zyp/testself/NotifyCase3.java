package com.zyp.testself;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/11 15:58
 */
class Print3 {
    //判断标志位
    private int flag = 1;

    public synchronized void printA() {
        for (int i = 0; i < 10; i++) {
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
            System.out.print("A");
        }
    }

    public synchronized void printB() {
        for (int i = 0; i < 10; i++) {
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
            System.out.print("B");
        }
    }

    public synchronized void printC() {
        for (int i = 0; i < 10; i++) {
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
            System.out.print("C");
        }
    }
}

public class NotifyCase3 {
    public static void main(String[] args) {
        Print3 print3 = new Print3();
        new Thread(print3::printA).start();
        new Thread(print3::printB).start();
        new Thread(print3::printC).start();
    }
}
