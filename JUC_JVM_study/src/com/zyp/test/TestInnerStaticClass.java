package com.zyp.test;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/12/5 23:38
 */
public class TestInnerStaticClass {
    static final class testInner{
        int a;
    }

    public static void main(String[] args) {
        testInner testInner = new testInner();
        System.out.println(testInner.a);
    }
}
