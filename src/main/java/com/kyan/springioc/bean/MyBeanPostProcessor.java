package com.kyan.springioc.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author kyan
 * @date 2019/10/31
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor() {
        super();
        System.out.println("MyBeanPostProcessor 调用构造器...");
    }

    //实现BeanPostProcessor接口
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean")) {
            System.out.println("BeanPostProcessor#postProcessBeforeInitialization 被调用...");
        }
        return bean;
    }

    //实现BeanPostProcessor接口
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean")) {
            System.out.println("BeanPostProcessor#postProcessAfterInitialization 被调用...");
        }
        return bean;
    }
}
