package com.kyan.springioc.dao.impl;

import com.kyan.springioc.dao.UserAccountDao;

/**
 * @author kyan
 * @date 2019/10/29
 */
public class UserAccountDaoImpl implements UserAccountDao {

    @Override
    public void addUserAccount() {
        System.out.println("Add a new user account");
    }
}
