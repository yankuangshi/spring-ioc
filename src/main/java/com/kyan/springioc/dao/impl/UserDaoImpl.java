package com.kyan.springioc.dao.impl;

import com.kyan.springioc.dao.UserDao;

/**
 * @author kyan
 * @date 2019/10/29
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void addUser() {
        System.out.println("Add a new user");
    }
}
