package com.zcy.dao;

import com.zcy.domain.Userinfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zcy on 2018/8/1.
 */
@Mapper
@Component
public interface LoginMapper {

    @Select("select * from usertab where username=#{username} and password=#{password}")
    List<Userinfo> check(@Param("username") String username, @Param("password") String password);

}
