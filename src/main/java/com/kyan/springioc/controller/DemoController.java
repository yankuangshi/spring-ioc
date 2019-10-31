package com.kyan.springioc.controller;

import com.kyan.springioc.bean.PrototypeBean;
import com.kyan.springioc.bean.RequestBean;
import com.kyan.springioc.bean.SessionBean;
import com.kyan.springioc.bean.SingletonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author kyan
 * @date 2019/10/29
 */
@Controller
public class DemoController {

    //分别注入以下4种不同作用域的bean实例
    @Autowired
    private SingletonBean singletonBean;
    @Autowired
    private PrototypeBean prototypeBean;
    @Autowired
    private RequestBean requestBean;
    @Autowired
    private SessionBean sessionBean;

    @RequestMapping(value = "/test")
    public void test() {
        System.out.println("DemoController#test()");
        print();
    }

    private void print() {
        System.out.println("first  time singleton:" + singletonBean);
        System.out.println("second time singleton:" + singletonBean);

        System.out.println("first  time prototype:" + prototypeBean);
        System.out.println("second time prototype:" + prototypeBean);

        System.out.println("first  time requestBean:" + requestBean);
        System.out.println("second time requestBean:" + requestBean);

        System.out.println("first  time sessionBean:" + sessionBean);
        System.out.println("second time sessionBean:" + sessionBean);

        System.out.println("===========================================");

        //first  time singleton:com.kyan.springioc.bean.SingletonBean@4462726c
        //second time singleton:com.kyan.springioc.bean.SingletonBean@4462726c
        //first  time prototype:com.kyan.springioc.bean.PrototypeBean@2966da50
        //second time prototype:com.kyan.springioc.bean.PrototypeBean@4bf24fbf
        //first  time requestBean:com.kyan.springioc.bean.RequestBean@20c2c33f
        //second time requestBean:com.kyan.springioc.bean.RequestBean@20c2c33f
        //first  time sessionBean:com.kyan.springioc.bean.SessionBean@68a5257
        //second time sessionBean:com.kyan.springioc.bean.SessionBean@68a5257
        //===========================================

        //first  time singleton:com.kyan.springioc.bean.SingletonBean@4462726c
        //second time singleton:com.kyan.springioc.bean.SingletonBean@4462726c
        //first  time prototype:com.kyan.springioc.bean.PrototypeBean@1c985840
        //second time prototype:com.kyan.springioc.bean.PrototypeBean@125fb5a3
        //first  time requestBean:com.kyan.springioc.bean.RequestBean@16603330
        //second time requestBean:com.kyan.springioc.bean.RequestBean@16603330
        //first  time sessionBean:com.kyan.springioc.bean.SessionBean@68a5257
        //second time sessionBean:com.kyan.springioc.bean.SessionBean@68a5257
        //===========================================
    }
}
