package com.kyan.springioc.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author kyan
 * @date 2019/10/29
 */
@Component
@Scope(value = "singleton") //默认就是单例
public class SingletonBean {
}
