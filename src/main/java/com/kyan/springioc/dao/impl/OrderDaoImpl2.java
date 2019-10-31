package com.kyan.springioc.dao.impl;

import com.kyan.springioc.dao.OrderDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author kyan
 * @date 2019/10/29
 */
//@Component 相同等效
@Repository("orderDao")
public class OrderDaoImpl2 implements OrderDao {

    @Override
    public void addOrder() {
        System.out.println("OrderDaoImpl2#addOrder()");
        System.out.println("Add a new order");
    }
}
