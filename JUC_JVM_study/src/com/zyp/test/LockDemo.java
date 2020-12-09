package com.zyp.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/13 15:55
 */
class Test2{
    private int variable;
    private final Lock lock = new ReentrantLock();
    public int getVariable() {
        return variable;
    }
    public void add(){
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        variable = variable + 1;
        //只锁住这段代码块中的代码
        lock.lock();
        try {

        }finally {
            lock.unlock();
        }
    }
}
public class LockDemo {
    public static void main(String[] args) {
        Test2 test2 = new Test2();
        IntStream.range(0,1000).forEach(s->new Thread(test2::add,String.valueOf(s)).start());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(test2.getVariable());
    }
}
