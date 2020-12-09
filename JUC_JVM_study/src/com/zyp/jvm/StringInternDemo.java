package com.zyp.jvm;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/16 15:01
 */
public class StringInternDemo {
    public static void main(String[] args) {
        String str1 = "123";
        String str2 = new String("123");
        String intern1 = str1.intern();
        String intern2 = str2.intern();

        //System.out.println(str2.getClass());

        System.out.println(str1.equals(str2));
        System.out.println("intern1 class: " + intern1.getClass());
        System.out.println("str1 class: " + str1.getClass());
        System.out.println(intern1 == str1);
        System.out.println("intern1 class: " + intern1.getClass());
        System.out.println("str2 class: " + str2.getClass());
        System.out.println(intern1 == str2);
        System.out.println("intern1 class: " + intern1.getClass());
        System.out.println("intern2 class: " + intern2.getClass());
        System.out.println(intern1 == intern2);

        System.out.println("str1 = " + str1);
        System.out.println("str2 = " + str2);
        System.out.println("intern1 = " + intern1);
        System.out.println("intern2 = " + intern2);
    }
}
