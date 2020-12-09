package com.zyp.thread_pool;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/15 20:28
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 执行很多短期异步任务，线程池根据需要创建新线程
 * 但在之前构建的线程可重用时将重用它们，可扩容（线程的最大容量为：Integer.MAX_VALUE）
 */
public class NewCacheThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 10; i++) {
                //推荐使用subimt
                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName());
                });
            }
        } finally {
            executorService.shutdown();
        }
    }
}
