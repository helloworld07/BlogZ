package com.zcy.dao;

import com.zcy.domain.Userinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zcy on 2018/9/3.
 */
@Mapper
@Component
public interface IdentifyMapper {
    @Select("select * from usertab where username=#{username} and email=#{email}")
    List<Userinfo> Identify(@Param("username") String username, @Param("email") String email);
}
