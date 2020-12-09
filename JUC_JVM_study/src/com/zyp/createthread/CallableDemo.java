package com.zyp.createthread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/10 16:21
 */
//资源类
class DealNum{
    private static int value = 0;
    public static int add(){
        value++;
        return value;
    }
}
public class CallableDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        int add = DealNum.add();
        return add;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableDemo callableDemo = new CallableDemo();
        //包装类
        FutureTask futureTask = new FutureTask(callableDemo);
        //FutureTask实现类Runnable接口
        new Thread(futureTask).start();
        System.out.println("======= befor get ======");
        //get()会阻塞当前线程直到获取到值，
        Integer value = (Integer) futureTask.get();
        System.out.println(value);
        System.out.println("======= after get ======");
    }
}
