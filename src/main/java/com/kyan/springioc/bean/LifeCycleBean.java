package com.kyan.springioc.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * @author kyan
 * @date 2019/10/31
 */
public class LifeCycleBean implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware,
        InitializingBean, DisposableBean {

    private String test;
    private BeanFactory beanFactory;
    private String beanName;
    private ClassLoader classLoader;

    public LifeCycleBean() {
        System.out.println("调用构造器...");
    }

    public void setTest(String test) {
        System.out.println("属性注入: " + test);
        this.test = test;
    }

    public void myInitMethod() {
        System.out.println("myInitMethod 被调用...");
    }

    public void myDestroyMethod() {
        System.out.println("myDestroyMethod 被调用...");
    }

    public void display() {
        System.out.println("模拟方法调用...");
        System.out.println("test属性值: " + test);
        System.out.println("beanName: " + beanName);
        System.out.println("是否为单例: " + beanFactory.isSingleton(beanName));
    }

    //实现BeanClassLoaderAware接口方法
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("BeanClassLoaderAware#setBeanClassLoader被调用...");
        this.classLoader = classLoader;
    }

    //实现BeanFactoryAware接口方法
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware#setBeanFactory 被调用...");
        this.beanFactory = beanFactory;
    }

    //实现BeanNameAware接口方法
    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware#setBeanName 被调用...");
        this.beanName = name;
    }

    //实现DisposableBean接口方法
    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy 被调用...");
    }

    //实现InitializingBean接口方法
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet 被调用...");
    }


}
