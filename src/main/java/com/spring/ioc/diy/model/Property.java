package com.spring.ioc.diy.model;

/**
 * <p>
 * </p>
 *
 * @author zongxin.xzx
 * @version 1.0
 * @date 创建时间 ：2016/6/5 9:33
 */
public class Property {
    private String name;
    private String ref;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", ref='" + ref + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
