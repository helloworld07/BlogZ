package com.zcy.service;

import com.zcy.dao.RegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zcy on 2018/8/3.
 */
@Service
public class RegisterService {
    @Autowired
    private RegisterMapper registerMapper;

    public int checkname(String username){
        int num = registerMapper.checkname(username);
        return num;
    }

    public int adduser(String username,String password,String email,String nickname,String telnum,String sex){
        int adduser = registerMapper.adduser(username, password, email, nickname, telnum, sex);
        return adduser;
    }
}
