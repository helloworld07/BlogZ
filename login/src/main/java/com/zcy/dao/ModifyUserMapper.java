package com.zcy.dao;

import com.zcy.domain.Userinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * Created by zcy on 2018/8/16.
 */
@Mapper
@Component
public interface ModifyUserMapper {

    @Update("update usertab set password = #{password} where id = #{id}")
    int updatepwd(@Param("password") String password, @Param("id") int id);

    @Select("select * from usertab where id = #{id}")
    Userinfo getUser(@Param("id") int id);

    @Update("update usertab set nickname=#{nickname},telnum=#{telnum},sex=#{sex},pub=#{pub},email=#{email} where id=#{id}")
    int updateinfo(@Param("nickname") String nickname, @Param("telnum") String telnum, @Param("sex") String sex, @Param("pub") String pub, @Param("email") String email, @Param("id") int id);

}