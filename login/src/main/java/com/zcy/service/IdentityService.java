package com.zcy.service;

import com.zcy.dao.IdentifyMapper;
import com.zcy.domain.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zcy on 2018/9/3.
 */
@Service
public class IdentityService {
    @Autowired
    private IdentifyMapper identifyMapper;

    public List<Userinfo> Identify(String username,String email){
        List<Userinfo> identify = identifyMapper.Identify(username, email);
        return identify;
    }
}
