package com.zyp.blocking_queue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/15 20:55
 */
class Test implements Delayed {
    /* 触发时间*/
    private long time;
    String name;

    public Test() {
    }

    public Test(long time, String name,TimeUnit timeUnit) {
        this.time = System.currentTimeMillis()+(time>0?timeUnit.toMillis(time):0);
        this.name = name;
    }

    /**
     * 每隔多久从队列中取出消息
     * @param unit 时间单位
     * @return 结果
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }

    /**
     * 比较队列中时间的大小
     * @param o 对列中的元素
     * @return 结果
     */
    @Override
    public int compareTo(Delayed o) {
        Test test = (Test) o;
        long diff = this.time - test.time;
        if (diff>0){
            return 1;
        }else if (diff<0){
            return -1;
        }else{
            return 0;
        }
    }
}
public class DelayedBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Test> blockingQueue = new DelayQueue();
        Test test1 = new Test(2,"test1",TimeUnit.SECONDS);
        Test test2 = new Test(4,"test2",TimeUnit.SECONDS);
        Test test3 = new Test(6,"test3",TimeUnit.SECONDS);

        blockingQueue.put(test1);
        blockingQueue.put(test2);
        blockingQueue.put(test3);

        System.out.println("begin time "+ LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        for (int i = 0; i < 3; i++) {
            Test test = blockingQueue.take();
            System.out.printf("name:{%s}\ttime:{%s}\n",test.name,LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        }
    }
}
