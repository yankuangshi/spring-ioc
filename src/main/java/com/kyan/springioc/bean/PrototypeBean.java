package com.kyan.springioc.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @author kyan
 * @date 2019/10/29
 */
@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS) //Create a class-based proxy (uses CGLIB).
public class PrototypeBean {
}
