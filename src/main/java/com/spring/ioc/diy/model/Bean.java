package com.spring.ioc.diy.model;

import java.util.List;

/**
 * <p></p>
 *
 * @author zongxin.xzx
 * @version 1.0
 * @date 创建时间 ：2016/6/5 9:34
 */
public class Bean {
    private String name;
    private String className;
    private String scope;
    private List<Property> properties;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", scope='" + scope + '\'' +
                ", properties=" + properties +
                '}';
    }
}
