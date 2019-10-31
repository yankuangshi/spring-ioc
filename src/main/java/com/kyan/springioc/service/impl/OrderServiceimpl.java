package com.kyan.springioc.service.impl;

import com.kyan.springioc.dao.OrderDao;
import com.kyan.springioc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author kyan
 * @date 2019/10/29
 */
public class OrderServiceimpl implements OrderService {

    //自动装配，标注成员变量
    @Autowired
    private OrderDao orderDao;

    //标注构造方法
//    @Autowired
//    public OrderServiceimpl(OrderDao orderDao) {
//        this.orderDao = orderDao;
//    }

    //标注Setter方法
//    @Autowired
//    public void setOrderDao(OrderDao orderDao) {
//        this.orderDao = orderDao;
//    }

    @Override
    public void addOrder() {
        System.out.println("OrderServiceImpl#addOrder()");
        orderDao.addOrder();
    }
}
