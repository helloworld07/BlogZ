package com.zcy.service;

import com.zcy.dao.LoginMapper;
import com.zcy.domain.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zcy on 2018/8/1.
 */
@Service
public class LoginService {
    @Autowired
    private LoginMapper loginMapper;

    public List<Userinfo> check(String username, String password){
        List<Userinfo> list = loginMapper.check(username, password);
        return list;
    }
}
