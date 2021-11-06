package com.ELEC5620.util;

import com.ELEC5620.entity.Users;
import org.springframework.stereotype.Component;

/**
 * 持有用户信息，用于代替session对象，
 */
@Component
public class HostHolder {

    private ThreadLocal<Users> users = new ThreadLocal<>();

    public void setUser(Users users) {
        this.users.set(users);
    }

    public Users getUser() {
        return users.get();
    }

    public void clear(){
        users.remove();
    }
}
