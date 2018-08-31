package com.zcy.controller;

import com.zcy.domain.Userinfo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by zcy on 2018/8/29.
 */
@Component
public class LocalUser {

    private static ThreadLocal<Userinfo> local = new ThreadLocal<Userinfo>();

    public Userinfo getUserinfo() {
        return local.get();
    }

    public void setUserInfo(Userinfo userinfo) {
        local.set(userinfo);
    }

    public void clear() {
        local.remove();
    }
}
