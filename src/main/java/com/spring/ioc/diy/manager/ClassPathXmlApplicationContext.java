package com.spring.ioc.diy.manager;

import com.spring.ioc.diy.model.Bean;
import com.spring.ioc.diy.model.Property;
import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.*;

/**
 * <p>IOC容器的核心实现类</p>
 *
 * @author zongxin.xzx
 * @version 1.0
 * @date 创建时间 ：2016/6/5 9:29
 */
public class ClassPathXmlApplicationContext implements BeanFactory {
    private Map<String, Object> contextMap = new HashMap<String, Object>();
    private Map<String, Bean> beanMap;

    public ClassPathXmlApplicationContext() {
        generateContext();
    }

    public Object getBean(String beanName) {
        return contextMap.get(beanName);
    }

    public Map<String, Bean> parseXml(String path) {
        Map<String, Bean> map = new HashMap<String, Bean>();
        SAXReader reader = new SAXReader();
        InputStream in = ClassPathXmlApplicationContext.class.getResourceAsStream(path);
        Document document = null;
        try {
            document = reader.read(in);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        for (Iterator it = root.elementIterator(); it.hasNext(); ) {
            Bean bean = new Bean();
            Element element = (Element) it.next();
            String name = element.attributeValue("name");
            bean.setName(name);
            String className = element.attributeValue("class");
            bean.setClassName(className);
            String scope = element.attributeValue("scope");
            if (scope.equals("")) {
                bean.setScope("singleton");
            } else {
                bean.setScope(scope);
            }
            //解析子元素property
            List<Property> list = new ArrayList<Property>();
            for (Iterator propertys = element.elementIterator(); propertys.hasNext(); ) {
                Element propertyNode = (Element) propertys.next();
                Property property = new Property();
                String propName = propertyNode.attributeValue("name");
                property.setName(propName);
                String value = propertyNode.attributeValue("value");
                property.setValue(value);
                String ref = propertyNode.attributeValue("ref");
                property.setRef(ref);
                //将解析的子元素装载到list中
                list.add(property);
            }
            bean.setProperties(list);
            map.put(name, bean);
        }
        return map;
    }

    public void generateContext() {
        beanMap = parseXml("/applicationContext.xml");
        //逐渐递归初始化
        for (Map.Entry<String, Bean> entry : beanMap.entrySet()) {
            String name = entry.getKey();
            Bean bean = entry.getValue();
            try {
                generateBean(name, bean);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public Object generateBean(String name, Bean bean) throws Exception {
        if (contextMap.get(name) != null) {
            return contextMap.get(name);
        }
        //将类进行实例化操作
        String className = bean.getClassName();
        Class beanClass = Class.forName(className);
        Object object = beanClass.newInstance();
        contextMap.put(name, object);
        //类实例化以后对属性进行相关的赋值操作
        List<Property> listProperties = bean.getProperties();
        for (Property property : listProperties) {
            String propertyName = property.getName();
            //PropertyDescriptor类表示JavaBean类通过存储器导出一个属性,主要方法:
            //getReadMethod获得读取属性名的方法,getWriteMethod()获得用于写入属性的方法
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, beanClass);
            Method method = propertyDescriptor.getWriteMethod();
            if (property.getValue() != null) {
                //使用BeanUtils进行注入值注入，防止数据类型不匹配的问题出现
                Map<String,String[]> valueMap = new HashMap<String, String[]>();
                valueMap.put(propertyName,new String[]{property.getValue()});
                //该种注入方式可以自动完成类型转换
                BeanUtils.populate(object,valueMap);
               // method.invoke(object, property.getValue());
            } else {
                Object beanObject = generateBean(property.getRef(), beanMap.get(property.getRef()));
                method.invoke(object, beanObject);
            }
        }
        return contextMap.get(name);
    }
}
