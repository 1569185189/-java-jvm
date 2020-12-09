package com.zyp.fork_join;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/18 22:31
 */
class Task extends RecursiveTask<Integer>{
    /**
     * 把复杂的计算拆成10个数的计算
     */
    private final static int ADJUST_VALUE = 10;
    private int start;
    private int end;
    private int result;

    public Task(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if ((end - start) <= ADJUST_VALUE) {
            for (int i = start; i <= end; i++) {
                result = result + i;
            }
        }else {
            int middle = (start+end)/2;
            Task task1 = new Task(start,middle);
            Task task2 = new Task(middle+1,end);
            task1.fork();
            task2.fork();
            result = task1.join()+task2.join();
        }
        return result;
    }
}
public class ForkJoinDemo{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Task task = new Task(0,100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> joinTask = forkJoinPool.submit(task);
        //Integer result = joinTask.get();
        System.out.println(joinTask.get());
        //关闭资源
        forkJoinPool.shutdown();
    }
}