package com.zyp.thread_pool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/15 20:44
 */
/*class ForDelay implements Delayed{

    private long time;

    public ForDelay(long time) {
        this.time = time+System.currentTimeMillis();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return time-System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        ForDelay forDelay = (ForDelay) o;
        long timeDiff = this.time - forDelay.time;
        if (timeDiff>0){
            return 1;
        }else if (timeDiff<0){
            return -1;
        }else {
            return 0;
        }
    }
}*/
class TestDelay implements Runnable {
    @Override
    public void run() {
        System.out.println("time: " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
    }
}

public class NewScheduledThreadPoolDemo {
    /**
     * 线程安全的队列
     */
    private static Queue<String> queue = new ConcurrentLinkedQueue<>();

    static {
        //入队列
        for (int i = 0; i < 10; i++) {
            queue.offer("task-" + i);
            //queue.
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newScheduledThreadPool(1);
        int i = 0;
        try {
            //while (!queue.isEmpty()){
            //思考：不能使用queue.size()，下面的代码中queue的长度在变化
            // (操作队列时尽量不要使用queue.size(),可能在操作过程中会改变队列的大小，推荐使用while()判断队列是否为空);
            //for (int i = 0; i < queue.size(); i++) { //错误
            for (; i < 10; i++) {
                final int temp = i;
                ScheduledFuture<String> schedule = ((ScheduledExecutorService) executorService).schedule(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        String task = queue.poll(); //使队列长度变短
                        System.out.println("queue size " + queue.size() + " and i = " + temp);
                        return task + " time: " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
                    }
                }, 2, TimeUnit.SECONDS);
                //不能在外部循环获取，要不然看不到任务定期执行的效果
                System.out.println(schedule.get());
            }
        } finally {
            //此处不能关闭线程池，否则看不任务定期执行的效果
            //关闭线程池
            //executorService.shutdown();
            if (i==10){
                executorService.shutdown();
            }
        }
    }
}
