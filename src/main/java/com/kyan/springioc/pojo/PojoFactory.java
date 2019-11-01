package com.kyan.springioc.pojo;

/**
 * @author kyan
 * @date 2019/10/31
 */
public class PojoFactory {

    public static User getUser() {
        User user = new User();
        user.setName("Bob");
        user.setAge(30);
        return user;
    }

    private User getUser2() {
        User user = new User();
        user.setName("Alice");
        user.setAge(30);
        return user;
    }
}
