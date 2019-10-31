package com.kyan.springioc.dao.impl;

import com.kyan.springioc.dao.OrderDao;

/**
 * @author kyan
 * @date 2019/10/29
 */
public class OrderDaoImpl implements OrderDao {

    @Override
    public void addOrder() {
        System.out.println("OrderDaoImpl#addOrder()");
        System.out.println("Add a new order");
    }
}
