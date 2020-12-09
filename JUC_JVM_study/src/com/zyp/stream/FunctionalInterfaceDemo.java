package com.zyp.stream;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/18 21:25
 */
public class FunctionalInterfaceDemo {
    public static void main(String[] args) {
        //消费型函数式接口
        Consumer<String> consumer = new Consumer<String>() {
            //有入参，无返回值
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("This is a Consumer FunctionalInterface");
        //供给型函数式接口
        Supplier<String> supplier = new Supplier<String>() {
            //无入参，有返回值
            @Override
            public String get() {
                return "This is a Supplier FunctionalInterface";
            }
        };
        System.out.println(supplier.get());
        //方法型函数式接口
        Function<String,Integer> function = new Function<String, Integer>() {
            //有入参(String)，有返回值(Integer)
            @Override
            public Integer apply(String s) {
                System.out.println(s);
                return 0;
            }
        };
        function.apply("This is a Function FunctionalInterface");
        //判断型函数式接口
        Predicate<Integer> predicate = new Predicate<Integer>() {
            //有入参，返回值为boolean类型
            @Override
            public boolean test(Integer integer) {
                return integer>0;
            }
        };
        System.out.println(predicate.test(2));
        //含有很多函数式接口
    }
}
