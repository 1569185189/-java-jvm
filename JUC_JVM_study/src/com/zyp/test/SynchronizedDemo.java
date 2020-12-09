package com.zyp.test;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/13 15:55
 */
class Test1{
    private int variable;

    public void add(){
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        variable = variable + 1;
        //只锁住这段代码块中的代码
        synchronized (this){

        }
    }
    public int getVariable() {
        return variable;
    }
}
public class SynchronizedDemo {
    public static void main(String[] args) {
        Test1 test1 = new Test1();
        IntStream.range(0,1000).forEach(s-> new Thread(test1::add,Integer.toString(s)).start());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(test1.getVariable());
    }
}
