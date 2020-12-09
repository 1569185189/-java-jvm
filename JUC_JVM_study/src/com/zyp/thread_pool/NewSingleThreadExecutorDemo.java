package com.zyp.thread_pool;

import java.util.concurrent.*;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/15 20:03
 */
//一个任务一个任务的执行，一池一线程
public class NewSingleThreadExecutorDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //创建两个线程
        try {
            Runnable runnable = () -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("This is Runnable");
            };
            Callable<String> callable = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "This is Callable";
                }
            };
            //提交并执行任务
            Future<?> submit1 = executorService.submit(runnable);
            try {
                //因为传入的是一个Runnable的实现类对象所以调用get()方法返回的是null
                System.out.println("Runnable " + submit1.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            //提交并执行任务
            Future<String> submit = executorService.submit(callable);
            try {
                //因为传入的是一个Callable的实现类对象所以调用get()方法返回的是call()方法的返回值
                System.out.println(submit.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } finally {
            //关闭线程池
            executorService.shutdown();
        }
    }
}
