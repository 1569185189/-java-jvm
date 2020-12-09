package com.zyp.thread_pool;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/15 16:56
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * 执行长期任务性能好，创建一个线程池，一池有N个固定数量的线程，有固定线程数的线程池
 */
public class NewFixedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            executorService.execute(() -> System.out.println("This is Runnable anonymous inner class"));
            Callable<String> callable1 = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "This is Callable anonymous inner class";
                }
            };
            Callable<String> callable2 = ()->{return "This is Callable for lambda";};
            //Callable<String> callable2 = ()->"This is Callable for lambda";
            try {
                //执行Callable创建的线程，返回Future的集合对象
                List<Future<String>> futures = executorService.invokeAll(Arrays.asList(callable1, callable2));
                //输出call方法的返回值
                futures.forEach(s-> {
                    try {
                        //调用get()方法会阻塞线程
                        System.out.println(s.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            //关闭线程池
            executorService.shutdown();
        }
    }
}
