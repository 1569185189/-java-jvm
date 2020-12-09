package com.zyp.juc;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.ThreadPoolExecutor.*;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/4 22:30
 */
//资源类
//***高内聚，低耦合***
class Ticket2{
    //锁应该只有一把，不能写在实例方法中(这样锁就有很多把了)
    //待实践
    //此所为当前类的实例对象锁，如果要实现当前类的Class锁只需要加上static关键字修饰即可
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

//线程池实现
public class SaleTicketsByPools {

    public static void main(String[] args) {
        //静态内部类
        ThreadPoolExecutor.DiscardPolicy discardPolicy = new ThreadPoolExecutor.DiscardPolicy();
        Random random = new Random();
        Ticket2 ticket2 = new Ticket2();
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(3,5 ,60 ,
                        TimeUnit.SECONDS,new ArrayBlockingQueue<>(10),
                        Executors.defaultThreadFactory(),
                        discardPolicy);
        threadPoolExecutor.execute(()->{
            for (int i = 0; i < 40; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(random.nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ticket2.decreaseTickets();
            }
        });
        threadPoolExecutor.execute(()->{
            for (int i = 0; i < 40; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(random.nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ticket2.decreaseTickets();
            }
        });
        threadPoolExecutor.execute(()->{
            for (int i = 0; i < 40; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(random.nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ticket2.decreaseTickets();
            }
        });
    }
}
