package com.zyp.stream;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/18 21:40
 */
public class FunctionalInterfaceDemo2 {
    public static void main(String[] args) {
        //有泛型的函数式接口简写需带上泛型
        //Consumer<String> consumer = (String s)-> System.out.println(s);
        //消费型函数式接口
        Consumer consumer = s-> System.out.println(s);
        consumer.accept("This is a Consumer FunctionalInterface");

        //供给型函数式接口
        Supplier<String> supplier = ()->"This is a Supplier FunctionalInterface";
        System.out.println(supplier.get());

        //函数型函数式接口
        Function<String,String> function = (s)->"This is a Function FunctionalInterface";
        System.out.println(function.apply(""));

        //判断型函数式接口
        Predicate<Integer> predicate = (s)->s>0;
        System.out.println(predicate.test(2));
    }
}
