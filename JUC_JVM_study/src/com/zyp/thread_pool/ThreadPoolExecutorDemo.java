package com.zyp.thread_pool;

import java.util.Iterator;
import java.util.concurrent.*;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/15 16:39
 */
class MyThread implements Runnable{
    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+": "+name);
    }
}
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        /**
         * 线程池相关参数：
         * corePoolSize：线程池中的常驻核心线程数
         * maximumPoolSize：线程池中能够容纳同时执行的最大线程数，此值必须大于等于1
         *                  (注：当队列已满时，创建的非核心线程，执行的是需要添加到队列中的任务，
         *                  而不是队列中的任务；ThreadPoolExecutor中的runWorker()方法)
         * keepAliveTime：空闲线程的存活时间，当线程池中线程数量超过corePoolSize，
         *                  当空闲时间到达keepAliveTime时，多余线程数会被销毁直到只剩下
         *                  corePoolSize个线程为止
         * unit：keepAliveTime的单位
         * workQueue：任务队列，被提交但尚未被执行的任务
         * threadFactory：表示生产线程池中工作线程的线程工厂，用于创建线程，一般默认即可
         * handler：拒绝策略，表示当队列已满并且工作线程数大于等于线程池的最大线程数(maximumPoolSize)时如何来拒绝
         *          请求执行的Runnable的策略
         *          有以下四种拒绝策略：(测试)
         *          AbortPolicy：直接抛出异常
         *          DiscardPolicy：直接丢弃任务
         *          DiscardOldestPolicy：丢弃最早没有被处理的任务，然后重试(即任务重新入队)
         *          CallerRunsPolicy：调用execute本身的线程运行任务(这个策略的缺点就是可能会阻塞主线程(main线程))
         */
        ExecutorService executorService = new ThreadPoolExecutor(2,
                3,
                0,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        try {
            for (int i = 0; i < 8; i++) {
                System.out.println("添加第"+(i+1)+"个任务");
                executorService.execute(new MyThread("线程"+(i+1)));
                //输出队列中等待的任务信息，分析线程池运行过程
                Iterator iterator = ((ThreadPoolExecutor) executorService).getQueue().iterator();
                while (iterator.hasNext()){
                    MyThread thread = (MyThread) iterator.next();
                    System.out.println("列表："+thread.getName());
                }
            }
        } finally {
            executorService.shutdown();
        }
    }
}
