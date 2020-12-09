package com.zyp.juc;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/4 21:54
 */
//资源类
//***高内聚，低耦合***
class Ticket{
    //锁应该只有一把，不能写在实例方法中(这样锁就有很多把了)
    private final Lock lock = new ReentrantLock();
    private int tickets = 30;
    public int getTickets(){
        return tickets;
    }
    //synchronized版
    /*public synchronized void decreaseTickets(){
        if (tickets>0){
            tickets--;
            System.out.println(Thread.currentThread().getName()+"卖票员卖了第"+(30-tickets)+"张票，剩余票数"+tickets);
        }
    }*/
    //lock版
    public void decreaseTickets(){
        lock.lock();
        try {
            if(tickets>0){
                tickets--;
                System.out.println(Thread.currentThread().getName()+"卖票员卖了第"+(30-tickets)+"张票，剩余票数"+tickets);
            }
        }finally {
            lock.unlock();
        }
    }
}

public class SaleTickets {
    public static void main(String[] args) {
        Random random = new Random();
        Ticket ticket = new Ticket();
        System.out.println("开始卖票，总票数："+ticket.getTickets());
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(random.nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ticket.decreaseTickets();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(random.nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ticket.decreaseTickets();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(random.nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ticket.decreaseTickets();
            }
        },"C").start();
    }
}
