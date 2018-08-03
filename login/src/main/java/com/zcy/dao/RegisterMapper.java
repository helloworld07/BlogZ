package com.zcy.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by zcy on 2018/8/3.
 */
@Mapper
@Component
public interface RegisterMapper {
    @Select("select count(*) from usertab where username=#{username}")
    int checkname(String username);

    @Insert("insert into usertab (username,password,email,nickname,telnum,sex) values (#{username},#{password},#{email},#{nickname},#{telnum},#{sex})")
    int adduser(@Param("username") String username,@Param("password")String password,@Param("email")String email,@Param("nickname")String name ,@Param("telnum")String telnum,@Param("sex")String sex);
}
