package com.zcy.dao;

import com.zcy.domain.LogInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by zcy on 2018/8/23.
 */
@Mapper
@Component
public interface LogDao {

    @Insert("insert into usertrack (userid,classname,operation,url,time,ipadd,args) values (#{userid},#{classname},#{operation},#{url},#{time},#{ipadd},#{args})")
    int insertTrack(LogInfo logInfo);

}

