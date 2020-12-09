package com.zyp.jvm;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/10 16:32
 */
class Student{
    private Integer sid;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
}
//类加载器
public class ClassLoadCase {
    public static void main(String[] args) {
        Student student = new Student();
        //启动类加载器C++编写
        System.out.println(student.getClass().getClassLoader().getParent().getParent());
        //扩展类加载器Java编写
        System.out.println(student.getClass().getClassLoader().getParent());
        //应用程序类加载器
        System.out.println(student.getClass().getClassLoader());
    }
}
