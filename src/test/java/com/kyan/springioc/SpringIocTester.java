package com.kyan.springioc;

import com.kyan.springioc.bean.LifeCycleBean;
import com.kyan.springioc.bean.MyBeanFactoryPostProcessor;
import com.kyan.springioc.bean.MyBeanPostProcessor;
import com.kyan.springioc.config.BeanConfiguration;
import com.kyan.springioc.pojo.User;
import com.kyan.springioc.service.OrderService;
import com.kyan.springioc.service.UserAccountService;
import com.kyan.springioc.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author kyan
 * @date 2019/10/29
 */
public class SpringIocTester {

    /**
     * 测试通过解析XML setter方法注入
     */
    @Test
    public void testDIBySetter() {
        //默认查找classpath路径下的文件
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-ioc.xml");
        //默认为项目工作路径，即项目根目录
//        FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext("src/test/resources/spring/spring-ioc.xml");
        //也可以读取classpath下的文件，需要指明前缀"classpath"
        FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:spring/spring-ioc.xml");
        UserService userService = (UserService) ctx.getBean("userService");
        userService.createUser();
        //UserServiceImpl#createUser()
        //add user...

        UserService userService2 = (UserService) ctx.getBean("userService");
        System.out.println(userService);
        System.out.println(userService2);
        //com.kyan.springioc.service.impl.UserServiceImpl@77f99a05
        //com.kyan.springioc.service.impl.UserServiceImpl@77f99a05
        //多次获取Bean并不会创建不同的UserService实例，因为Spring默认是创建单例
    }

    /**
     * 测试通过解析XML 构造器注入
     */
    @Test
    public void testDIByConstructor() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "spring/spring-ioc.xml", "spring/spring-ioc2.xml"); //可以传递数组
        UserService userService = (UserService) ctx.getBean("userService");
        UserAccountService userAccountService = (UserAccountService) ctx.getBean("userAccountService");
        userService.createUser();
        userAccountService.createUserAccount();
    }

    /**
     * 测试通过注解@Bean加载bean
     */
    @Test
    public void testLoadBeanByAnnotationConfiguration() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        UserService bean1 = (UserService) ctx.getBean("userService");
        UserService bean2 = (UserService) ctx.getBean("userService");
        bean1.createUser();
        System.out.println(bean1 == bean2);
    }

    /**
     * 测试通过自动装配@Autowired注入
     */
    @Test
    public void testDIByAutowiredAnnotation() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-ioc.xml");
        OrderService orderService = (OrderService) ctx.getBean("orderService");
        orderService.addOrder();
    }

    /**
     * 测试通过注解@Component方式初始化Bean
     */
    @Test
    public void testInstantiationBeanByComponentScan() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-ioc3.xml");
        OrderService orderService = (OrderService) ctx.getBean("orderService");
        orderService.addOrder();
    }

    @Test
    public void testInstantiationBeanByFactoryMethod() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-ioc4.xml");
        User user1 = (User)ctx.getBean("user1");
        User user2 = (User)ctx.getBean("user2");
        System.out.println(user1);  //User{name='Bob', age=30}
        System.out.println(user2);  //User{name='Alice', age=30}
    }

    /**
     * ApplicationContext容器可以在其bean定义中自动检测所有的BeanPostProcessor，并自动完成注册，同时将它们应用到随后创建的任何bean中。
     * 对于ApplicationContext容器，调用registerShutdownHook即可关闭容器
     */
    @Test
    public void testBeanLifeCycle() {
        System.out.println("开始启动容器");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-ioc5.xml");
        System.out.println("容器初始化成功");
        LifeCycleBean lifeCycleBean = (LifeCycleBean) ctx.getBean("lifeCycleBean");
        lifeCycleBean.display();
        System.out.println("开始关闭容器");
        ((ClassPathXmlApplicationContext)ctx).registerShutdownHook();
        //开始启动容器
        //MyBeanPostProcessor 调用构造器...
        //调用构造器...
        //属性注入...
        //BeanNameAware#setBeanName 被调用...
        //BeanClassLoaderAware#setBeanClassLoader被调用...
        //BeanFactoryAware#setBeanFactory 被调用...
        //BeanPostProcessor#postProcessBeforeInitialization 被调用...
        //InitializingBean#afterPropertiesSet 被调用...
        //myInitMethod 被调用...
        //BeanPostProcessor#postProcessAfterInitialization 被调用...
        //容器初始化成功
        //模拟方法调用...
        //test属性值:test
        //beanName: lifeCycleBean
        //是否为单例: true
        //开始关闭容器
        //DisposableBean#destroy 被调用...
        //myDestroyMethod 被调用...
    }

    /**
     * BeanFactory容器是不支持自动注册BeanPostProcessor的，需要我们手动调用addBeanPostProcessor()进行注册，
     * 注册后的BeanPostProcessor适用于所有该BeanFactory创建的bean。
     *
     * 对于BeanFactory容器，我们需要主动调用destroySingletons()通知BeanFactory容器去执行相应的销毁方法
     */
    @Test
    public void testBeanLifeCycle2() {
        System.out.println("开始启动容器");
        ClassPathResource resource = new ClassPathResource("spring/spring-ioc6.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);
        factory.addBeanPostProcessor(new MyBeanPostProcessor());
        LifeCycleBean lifeCycleBean = (LifeCycleBean) factory.getBean("lifeCycleBean");
        lifeCycleBean.display();
        System.out.println("开始关闭容器");
        factory.destroySingletons();
        //开始启动容器
        //MyBeanPostProcessor 调用构造器...
        //调用构造器...
        //属性注入...
        //BeanNameAware#setBeanName 被调用...
        //BeanClassLoaderAware#setBeanClassLoader被调用...
        //BeanFactoryAware#setBeanFactory 被调用...
        //BeanPostProcessor#postProcessBeforeInitialization 被调用...
        //InitializingBean#afterPropertiesSet 被调用...
        //myInitMethod 被调用...
        //BeanPostProcessor#postProcessAfterInitialization 被调用...
        //模拟方法调用...
        //test属性值:test
        //beanName: lifeCycleBean
        //是否为单例: true
        //开始关闭容器
        //DisposableBean#destroy 被调用...
        //myDestroyMethod 被调用...
    }

    /**
     * 利用ApplicationContext自动识别配置文件中的BeanFactoryPostProcessor并且完成注册和调用
     */
    @Test
    public void testBeanLifeCycle3() {
        System.out.println("开始启动容器");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-ioc7.xml");
        System.out.println("容器初始化成功");
        LifeCycleBean lifeCycleBean = (LifeCycleBean) ctx.getBean("lifeCycleBean");
        lifeCycleBean.display();
        System.out.println("开始关闭容器");
        ((ClassPathXmlApplicationContext)ctx).registerShutdownHook();
        //开始启动容器
        //调用 MyBeanFactoryPostProcessor#postProcessBeanFactory...
        //容器中BeanDefinition的个数：3
        //BeanDefinitionName: lifeCycleBean
        //BeanDefinitionName: myBeanFactoryPostProcessor
        //BeanDefinitionName: myBeanPostProcessor
        //MyBeanPostProcessor 调用构造器...
        //调用构造器...
        //属性注入: welcome
        //BeanNameAware#setBeanName 被调用...
        //BeanClassLoaderAware#setBeanClassLoader被调用...
        //BeanFactoryAware#setBeanFactory 被调用...
        //BeanPostProcessor#postProcessBeforeInitialization 被调用...
        //InitializingBean#afterPropertiesSet 被调用...
        //myInitMethod 被调用...
        //BeanPostProcessor#postProcessAfterInitialization 被调用...
        //容器初始化成功
        //模拟方法调用...
        //test属性值: welcome
        //beanName: lifeCycleBean
        //是否为单例: true
        //开始关闭容器
        //DisposableBean#destroy 被调用...
        //myDestroyMethod 被调用...
    }

    @Test
    public void testBeanLifeCycle4() {
        System.out.println("开始启动容器");
        ClassPathResource resource = new ClassPathResource("spring/spring-ioc6.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);
        //实例化BeanFactoryPostProcessor，并手动进行注册调用
        BeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(factory);
        factory.addBeanPostProcessor(new MyBeanPostProcessor());
        LifeCycleBean lifeCycleBean = (LifeCycleBean) factory.getBean("lifeCycleBean");
        lifeCycleBean.display();
        System.out.println("开始关闭容器");
        factory.destroySingletons();
        //开始启动容器
        //调用 MyBeanFactoryPostProcessor#postProcessBeanFactory...
        //容器中BeanDefinition的个数：1            =>此时只有lifeCycleBean被load
        //BeanDefinitionName: lifeCycleBean
        //MyBeanPostProcessor 调用构造器...
        //调用构造器...
        //属性注入: welcome
        //BeanNameAware#setBeanName 被调用...
        //BeanClassLoaderAware#setBeanClassLoader被调用...
        //BeanFactoryAware#setBeanFactory 被调用...
        //BeanPostProcessor#postProcessBeforeInitialization 被调用...
        //InitializingBean#afterPropertiesSet 被调用...
        //myInitMethod 被调用...
        //BeanPostProcessor#postProcessAfterInitialization 被调用...
        //模拟方法调用...
        //test属性值: welcome
        //beanName: lifeCycleBean
        //是否为单例: true
        //开始关闭容器
        //DisposableBean#destroy 被调用...
        //myDestroyMethod 被调用...
    }
}
