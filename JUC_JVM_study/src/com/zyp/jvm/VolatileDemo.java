package com.zyp.jvm;

import java.util.concurrent.TimeUnit;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/13 10:40
 */
//资源类
class Variable {
    //共享变量, volatile修饰的变量能保证一个线程对该变量的操作，对其他线程可见(还有防止指令重排的作用)
    private volatile int variable;

    public int getVariable() {
        return variable;
    }

    public void setVariable(int variable) {
        this.variable = variable;
    }
}

public class VolatileDemo {
    public static void main(String[] args) {
        Variable variable = new Variable();
        variable.setVariable(10);
        //线程AAA
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            variable.setVariable(20);
            System.out.println(Thread.currentThread().getName() + " 修改了variable的值 " + variable.getVariable());
        }, "AAA").start();
        //main线程
        while (variable.getVariable() == 10) {
            /**
             * 一直阻塞，main线程并不知道AAA线程已经修改了variable的值，
             * main的本地工作内存variable还是10，这是由于线程对共享变量的操作只能在本地进行
             */
            /**
             * 当这个变量被volatile关键字修饰时，其他线程对这个变量的修改操作完成后，
             * 主内存会通知各线程重新同步该值
             */
        }
        System.out.println(Thread.currentThread().getName() + "执行完毕");
    }
}
