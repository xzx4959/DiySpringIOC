package com.spring.ioc.diy.manager;

/**
 * <p>
 * </p>
 *
 * @author zongxin.xzx
 * @version 1.0
 * @date 创建时间 ：2016/6/5 9:27
 */
public interface BeanFactory {
    Object getBean(String beanName);
}
