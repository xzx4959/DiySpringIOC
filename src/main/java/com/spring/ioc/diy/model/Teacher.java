package com.spring.ioc.diy.model;

/**
 * <p>
 * </p>
 *
 * @author zongxin.xzx
 * @version 1.0
 * @date ����ʱ�� ��2016/6/4 18:37
 */
public class Teacher {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
