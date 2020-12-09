package com.zyp.test;

import java.util.HashMap;
import java.util.Map;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/12 17:49
 */
public class Test {
    public static void main(String[] args) {
        /*System.out.println(2&2);
        System.out.println(4&2);
        System.out.println(6&2);
        System.out.println(8&2);
        System.out.println(10&2);
        System.out.println(220&2);
        System.out.println(222&2);*/

        /*char c = (char) 90;
        System.out.println(c);*/

        retry:
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                if (i == 8) {
                    //break retry 跳到retry处，且不再进入循环，跳出多个循环块
                    break retry;
                }
                System.out.println(i);
            }
        }
        retry1:
        for (int i = 0; i < 10; i++) {
            if (i == 2) {
                //continue retry 跳到retry处，且再次进入循环
                continue retry1;
            }
            if (i == 6) {
                continue retry1;
            }
            if (i == 8) {
                //break retry 跳到retry处，且不再进入循环
                break retry1;
            }
            System.out.println(i);
        }
    }
}
