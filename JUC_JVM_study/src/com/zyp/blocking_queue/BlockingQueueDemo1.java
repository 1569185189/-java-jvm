package com.zyp.blocking_queue;

import java.util.concurrent.*;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/15 13:52
 */
public class BlockingQueueDemo1 {
    public static void main(String[] args) throws InterruptedException {
        /**
         * BlockingQueue的核心方法
         */
        //throwExecptionMethod();
        //returnMethod();
        //blockingMethod();
        //blockingWithTimeMethod();

        //ArrayBlockingQueue：由数组结构组成的有界阻塞队列
        //BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);

        //LinkedBlockingQueue：由链表结构组成的有界阻塞队列，但大小默认值为Integer.MAX_VALUE
        //BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(3);
        //throwExecptionMethod(blockingQueue);

        //SynchronousQueue：不存储元素的阻塞队列，也即单个元素的阻塞队列
        BlockingQueue<Integer> blockingQueue = new SynchronousQueue<>();
        /*System.out.println(blockingQueue.add(1));
        System.out.println(blockingQueue.remove());*/
        new Thread(()-> {
            try {
               blockingQueue.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()-> {
            try {
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()-> {
            try {
                blockingQueue.put(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()-> {
            try {
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void blockingWithTimeMethod(BlockingQueue<Integer> blockingQueue) throws InterruptedException {
        /**
         * 超时退出的方法：offer(e,time,unit), poll(time,unit)
         * 当阻塞队列满时，队列会阻塞生产者线程一定时间，超过限时后生产者线程会退出
         * 当阻塞队列空时，队列会阻塞消费者线程一定时间，超过限时后消费者线程会退出
         */
        System.out.println(blockingQueue.offer(1));
        System.out.println(blockingQueue.offer(2));
        System.out.println(blockingQueue.offer(3));
        //队列已满，等待四秒生产者线程退出
        System.out.println(blockingQueue.offer(4,4, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        //队列已空，等待四秒消费者线程退出
        System.out.println(blockingQueue.poll(4,TimeUnit.SECONDS));
    }

    private static void blockingMethod(BlockingQueue<Integer> blockingQueue) throws InterruptedException {
        /**
         * 阻塞线程的方法：put(e), take()
         * 当阻塞队列满时，生产者线程继续往队列里put元素，队列会一直阻塞生产者线程直到put数据or响应中断退出
         * 当阻塞队列空时，消费者线程试图从队列里take元素，队列会一直阻塞消费者线程直到队列可用
         */
        blockingQueue.put(1);
        blockingQueue.put(2);
        blockingQueue.put(3);
        //队列已满，阻塞线程
        //blockingQueue.put(4);
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        //队列已空，阻塞线程
        //System.out.println(blockingQueue.take());
    }

    private static void returnMethod(BlockingQueue<Integer> blockingQueue) {
        /**
         * 返回Boolean值的方法：offer(e), poll(), peek()
         * 插入方法，成功ture失败false
         * 移除方法，成功返回出队列的元素，队列里没有就返回null
         */
        System.out.println(blockingQueue.offer(1));
        System.out.println(blockingQueue.offer(2));
        System.out.println(blockingQueue.offer(3));
        //添加失败，队列已满
        //System.out.println(blockingQueue.offer(4));
        //获取队头元素的值
        //System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        //移除失败队列已空，返回值为null
        System.out.println(blockingQueue.poll());
        //队列已空返回null
        System.out.println(blockingQueue.peek());
    }

    private static void throwExecptionMethod(BlockingQueue<Integer> blockingQueue) {
        /**
         * 抛出异常的操作方法：add(e), remove(), element()
         * 当阻塞队列满时，再往队列里add插入元素会抛IllegalStateException:Queue full
         * 当阻塞队列空时，再往队列里remove移除元素会抛NoSuchElementException
         */
        //添加元素到队列中
        System.out.println(blockingQueue.add(1));
        System.out.println(blockingQueue.add(2));
        System.out.println(blockingQueue.add(3));
        //获得队头元素的值
        System.out.println(blockingQueue.element());
        //超出容量抛出异常：java.lang.IllegalStateException: Queue full
        //System.out.println(blockingQueue.add(4));
        //从队列中移除元素
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //队列中已经没有元素，移除失败抛出异常：java.util.NoSuchElementException
        //System.out.println(blockingQueue.remove());
    }
}
