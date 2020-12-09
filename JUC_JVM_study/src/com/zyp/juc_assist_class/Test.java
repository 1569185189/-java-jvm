package com.zyp.juc_assist_class;

import java.util.concurrent.CountDownLatch;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/11 16:39
 */
public class Test {
    //jdk文档介绍用法
    /*class Driver {
        void main() throws InterruptedException {
            CountDownLatch startSignal = new CountDownLatch(1);
            CountDownLatch doneSignal = new CountDownLatch(N);
            for (int i = 0; i < N; ++i)
                new Thread(new Worker(startSignal, doneSignal)).start();
            doSomethingElse();
            startSignal.countDown();
            doSomethingElse();

            doneSignal.await();
        }
    }

    class Worker implements Runnable {
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;

        Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        public void run() {
            try {
                startSignal.await();
                doWork();
                doneSignal.countDown();
            } catch (InterruptedException ex) {
            }
            return;
        }

        void doWork() { ...}
    }*/
    public static void main(String[] args) {
        int a = 0;
        int b = 1;
        System.out.println(a=b=3);
    }
}
