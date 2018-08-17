package com.zcy.service;

import com.zcy.dao.ModifyUserMapper;
import com.zcy.domain.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zcy on 2018/8/16.
 */
@Service
public class ModifyService {

    @Autowired
    private ModifyUserMapper modifyUserMapper;

    public int updatepwd(String password,int id){
        int count = modifyUserMapper.updatepwd(password, id);
        return count;
    }

    public Userinfo getUser(int id){
        Userinfo user = modifyUserMapper.getUser(id);
        return user;
    }

    public int updateInfo(String nickname,String telnum,String sex,String pub,String email,int id){
        int count = modifyUserMapper.updateinfo(nickname, telnum, sex, pub, email, id);
        return count;
    }
}
