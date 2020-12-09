package com.zyp.read_write_lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/12 17:32
 */

/**
 * 与传统锁不同的是读写锁的规则是可以共享读，但只能一个写，
 * 总结起来为：读读不互斥，读写互斥，写写互斥，
 * 而一般的独占锁是：读读互斥，读写互斥，写写互斥
 */
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>(8);
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        //获取写锁
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在写 " + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " 写入成功 " + key);
        }finally {
            //释放写锁
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        //获取读锁
        readWriteLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + " 正在读 " + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName() + " 读取成功 " + value);
        }finally {
            //释放读锁
            readWriteLock.readLock().unlock();
        }
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 6; i++) {
            final int temp = i;
            new Thread(() -> myCache.put("key " + String.valueOf(temp), temp), Integer.toString(i)).start();
        }
        System.out.println("=====================");
        for (int i = 0; i < 6; i++) {
            final int temp = i;
            new Thread(() -> myCache.get("key " + String.valueOf(temp)), Integer.toString(i)).start();
        }
        /**
         * 结果发现写是独占的，读是共享的
         */
    }
}
