package com.zyp.list;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/10 10:08
 */
public class TheClassCase {
    public static void main(String[] args) {
        //线程不安全
        //List<String> list = new ArrayList<>();    //java.util.ConcurrentModificationException
        //线程安全
        //List<String> list = new Vector<>();   //不建议使用，效率低下
        //List<String> list = Collections.synchronizedList(new ArrayList<>());    //得到一个线程安全的集合
        /*List<String> list = new CopyOnWriteArrayList<>();   //读(get)操作不加锁，写(add)操作加锁，写时复制(推荐使用)
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));  //写操作
                System.out.println(list);   //读操作
            },Integer.toString(i)).start();
        }*/

        //线程不安全
        //Set<String> set = new HashSet<>();      //java.util.ConcurrentModificationException
        //线程安全
        //Set<String> set = Collections.synchronizedSet(new HashSet<>());
        //Set<String> set = new CopyOnWriteArraySet<>();    //推荐使用
        /*for (int i = 0; i < 30; i++) {
            new Thread(()->{
               set.add(UUID.randomUUID().toString().substring(0,8));
               System.out.println(set);
            },String.valueOf(i)).start();
        }*/

        //线程不安全
        //Map<String, String> map = new HashMap<>();   //java.util.ConcurrentModificationException
        //线程安全
        //Map<String,String> map = Collections.synchronizedMap(new HashMap<>());
        Map<String,String> map = new ConcurrentHashMap<>(); //推荐使用
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                String key = UUID.randomUUID().toString().substring(0,8);
                String value = UUID.randomUUID().toString().substring(0,8);
                map.put(key,value);
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
