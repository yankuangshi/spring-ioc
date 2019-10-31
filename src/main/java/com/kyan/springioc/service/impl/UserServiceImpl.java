package com.kyan.springioc.service.impl;

import com.kyan.springioc.dao.UserDao;
import com.kyan.springioc.service.UserService;

/**
 * @author kyan
 * @date 2019/10/29
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    //Setter注入
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void createUser() {
        System.out.println("UserServiceImpl#createUser()");
        userDao.addUser();
    }
}
