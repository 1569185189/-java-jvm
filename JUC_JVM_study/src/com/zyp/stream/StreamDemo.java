package com.zyp.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/18 21:22
 */
public class StreamDemo {
    private static List<User> users = new ArrayList<>();
    static {
        users.add(new User(1,"abcd",20,"man"));
        users.add(new User(2,"bbcd",24,"man"));
        users.add(new User(3,"cbcd",23,"woman"));
        users.add(new User(4,"fbcd",26,"woman"));
        users.add(new User(5,"ebcd",25,"man"));
        users.add(new User(6,"dbcd",22,"woman"));
    }
    public static void main(String[] args) {
        List<String> collect = users.stream().filter(user -> user.getAge() >= 24)
                .filter(user -> "man".equals(user.getSex()))
                .sorted((user1,user2)->user2.getName().compareTo(user1.getName()))
                .limit(1)
                .map(User::getName)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
}

class A implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}

class B implements Comparable{

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
class User{
    private Integer id;
    private String name;
    private Integer age;
    private String sex;

    public User() {
    }

    public User(Integer id, String name, Integer age, String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}