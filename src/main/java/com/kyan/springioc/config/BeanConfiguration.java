package com.kyan.springioc.config;

import com.kyan.springioc.dao.UserDao;
import com.kyan.springioc.dao.impl.UserDaoImpl;
import com.kyan.springioc.service.UserService;
import com.kyan.springioc.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kyan
 * @date 2019/10/29
 */
@Configuration
public class BeanConfiguration {

    /**
     * 等效于
     * <bean name="userDao" class="com.kyan.springioc.dao.impl.UserDaoImpl"/>
     */
    @Bean
    public UserDao userDao() {
        return new UserDaoImpl();
    }

    /**
     * 等效于
     * <bean name="userService" class="com.kyan.springioc.service.impl.UserServiceImpl">
     *  <property name="userDao" ref="userDao"/>
     * </bean>
     * @return
     */
    @Bean
    public UserService userService() {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDao(userDao());
        return userService;
    }
}
