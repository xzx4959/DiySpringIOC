package com.spring.ioc.diy.model;

/**
 * <p>
 * </p>
 *
 * @author zongxin.xzx
 * @version 1.0
 * @date 创建时间 2016/6/4 18:36
 */
public class Student {
    private String name;
    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}
