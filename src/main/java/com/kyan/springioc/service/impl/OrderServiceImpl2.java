package com.kyan.springioc.service.impl;

import com.kyan.springioc.dao.OrderDao;
import com.kyan.springioc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author kyan
 * @date 2019/10/29
 */
//@Component 相同等效
@Service("orderService")
public class OrderServiceImpl2 implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void addOrder() {
        System.out.println("OrderServiceImpl2#addOrder()");
        orderDao.addOrder();
    }
}
