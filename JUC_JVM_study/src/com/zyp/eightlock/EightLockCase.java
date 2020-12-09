package com.zyp.eightlock;

import java.util.concurrent.TimeUnit;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/10 11:01
 */
public class EightLockCase {
    public static void main(String[] args) {
        //同一对象的情况
        //资源一
        /*final Resource1 resource1 = new Resource1();
        new Thread(resource1::study,"AAA").start();
        new Thread(resource1::play,"BBB").start();*/
        //资源二
        /*final Resource2 resource2 = new Resource2();
        new Thread(resource2::study,"AAA").start();
        new Thread(resource2::play,"BBB").start();*/
        //资源三
        /*
        final Resource3 resource3 = new Resource3();
        new Thread(Resource3::study,"AAA").start();
        new Thread(resource3::play,"BBB").start();
        new Thread(resource3::others,"CCC").start();//调用非同步方法，没有资源竞争问题
        */
        //资源四
        /*
        final Resource4 resource4 = new Resource4();
        new Thread(Resource4::study,"AAA").start();
        new Thread(Resource4::play,"BBB").start();
        new Thread(resource4::others,"CCC").start();//调用非同步方法，没有资源竞争问题
        */

        //不同对象的情况
        //资源一
        //锁的对象不同，无资源竞争问题
        /*final Resource1 resource1 = new Resource1();
        final Resource1 resource2 = new Resource1();
        new Thread(resource1::study,"AAA").start();
        new Thread(resource2::play,"BBB").start();*/

        /**
         * 如果是使用不同类的实例对象调用同步静态方法，会存在资源竞争问题
         * 因为同步静态方法的锁是Class类，这个类只有一个
         */
    }
}
/**
 * 1、这种情况下，多条线程同时访问同一个类的不同的同步方法，存在资源竞争问题，
 *   即未获取到锁的线程，必须等待获取到锁的线程执行完毕释放锁后，获取到锁后才能继续执行
 */
//资源类1
class Resource1 {
    public synchronized void  study(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" is studying");
    }
    public synchronized void play(){
        System.out.println(Thread.currentThread().getName()+" is playing");
    }
}
/**
 * 2、这种情况下，多条线程同时访问同一个类的同步方法和非同步方法，不存在资源竞争问题，
 *   即未获取到锁的线程，无须等待获取到锁的线程执行完毕释放锁后，就能执行
 */
//资源类2
class Resource2 {
    public synchronized void  study(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" is studying");
    }
    public void play(){
        System.out.println(Thread.currentThread().getName()+" is playing");
    }
}
/**
 * 3、这种情况下，多条线程同时访问同一个类的同步静态方法和同步方法，不存在资源竞争问题，
 *   因为同步静态方法锁的对象为Class对象，而同步方法锁的对象为类的实例对象(new出来的)，
 *   所以不存在资源竞争问题
 */
//资源类3
class Resource3 {
    public static synchronized void  study(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" is studying");
    }
    public synchronized void play(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" is playing");
    }
    public void others(){
        System.out.println(Thread.currentThread().getName()+" others");
    }
}
/**
 * 4、这种情况下，多条线程同时访问同一个类的同步静态方法，存在资源竞争问题(竞争同一个Class对象)，
 *   即未获取到锁的线程，必须等待获取到锁的线程执行完毕释放锁后，获取到锁后才能继续执行
 */
//资源类4
class Resource4 {
    public static synchronized void  study(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" is studying");
    }
    public static synchronized void play(){
        System.out.println(Thread.currentThread().getName()+" is playing");
    }
    public void others(){
        System.out.println(Thread.currentThread().getName()+" others");
    }
}