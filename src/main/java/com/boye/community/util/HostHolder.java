package com.boye.community.util;

import com.boye.community.entity.User;
import com.boye.community.service.UserService;
import org.springframework.stereotype.Component;

/**
 * 持有用户信息2，用于代替session对象
 */
@Component
public class HostHolder {

    private ThreadLocal<User> users= new ThreadLocal<User>();

    public void setUser(User user){
        users.set(user);
    }

    public User getUser(){
       return users.get();
    }

    public void clear(){
        users.remove();
    }

}
