package com.kyan.springioc.bean;

import jdk.nashorn.internal.objects.NativeUint8Array;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * BeanFactoryPostProcessor的机制相当于给了我们在bean实例化之前最后一次修改BeanDefinition的机会，我们可以利用这个对BeanDefinition
 * 进行一些额外操作，比如修改某些bean的属性，给某些bean增加一些其他的信息等操作
 *
 * @author kyan
 * @date 2019/11/1
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    /**
     * 该方法调用时间在所有BeanDefinition已经完成加载至BeanFactory之后，还未完成Bean实例化之前
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("调用 MyBeanFactoryPostProcessor#postProcessBeanFactory...");
        System.out.println("容器中BeanDefinition的个数：" + beanFactory.getBeanDefinitionCount());
        String[] bdNames = beanFactory.getBeanDefinitionNames();
        for (String bdName : bdNames) {
            System.out.println("BeanDefinitionName: " + bdName);
        }
        //可以通过反射获取所有的BeanDefinition
        try {
            DefaultListableBeanFactory factory = (DefaultListableBeanFactory) beanFactory;
            Field f = factory.getClass().getDeclaredField("beanDefinitionMap");
            f.setAccessible(true);
            Map<String, BeanDefinition> map = (Map<String, BeanDefinition>) f.get(factory);
            for (Map.Entry<String, BeanDefinition> entry : map.entrySet()) {
                System.out.println("BeanClassName: " + entry.getValue().getBeanClassName());
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        //获取指定的BeanDefinition
        BeanDefinition bd = beanFactory.getBeanDefinition("lifeCycleBean");
        MutablePropertyValues pvs = bd.getPropertyValues();
        pvs.addPropertyValue("test", "welcome");

    }
}
