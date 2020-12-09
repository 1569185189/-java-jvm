package com.zyp.test;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/15 21:22
 */
class A {
    private int a;
}

class B extends A {
    private int b;
}

public class Tests {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        //子类的引用指向父类，父类转换成子类，报错
        b = (B) a;
        //父类的引用指向子类，子类转换成父类，不报错
        a = b;
        Integer integer = (Integer) new Object();
    }
}
