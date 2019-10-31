package com.kyan.springioc.service.impl;

import com.kyan.springioc.dao.UserAccountDao;
import com.kyan.springioc.service.UserAccountService;

/**
 * @author kyan
 * @date 2019/10/29
 */
public class UserAccountServiceImpl implements UserAccountService {

    private UserAccountDao userAccountDao;

    //构造器注入
    public UserAccountServiceImpl(UserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }

    @Override
    public void createUserAccount() {
        System.out.println("UserAccountServiceImpl#createUserAccount()");
        userAccountDao.addUserAccount();
    }
}
