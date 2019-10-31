package com.kyan.springioc;

import com.kyan.springioc.config.BeanConfiguration;
import com.kyan.springioc.service.OrderService;
import com.kyan.springioc.service.UserAccountService;
import com.kyan.springioc.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author kyan
 * @date 2019/10/29
 */
public class SpringIocTester {

    /**
     * 测试通过解析XML注入
     */
    @Test
    public void testInjectByClassPathXml() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-ioc.xml");
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
     * 测试通过解析XML注入
     */
    @Test
    public void testInjectByClassPathXml2() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "spring/spring-ioc.xml", "spring/spring-ioc2.xml"); //可以传递数组
        UserService userService = (UserService) ctx.getBean("userService");
        UserAccountService userAccountService = (UserAccountService) ctx.getBean("userAccountService");
        userService.createUser();
        userAccountService.createUserAccount();
    }

    /**
     * 测试通过注解@Bean注入
     */
    @Test
    public void testInjectByAnnotationConfiguration() {
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
    public void testInjectByAutowiredAnnotation() {
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
}
