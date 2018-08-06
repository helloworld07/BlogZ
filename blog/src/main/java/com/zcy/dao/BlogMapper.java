package com.zcy.dao;

import com.zcy.domain.BlogInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zcy on 2018/8/4.
 */
@Mapper
@Component
public interface BlogMapper {
    @Select({"<script>",
            "SELECT * FROM paper",
            "WHERE 1=1",
            "<when test='author!=null'>",
            "AND author = #{author}",
            "</when>",
            "</script>"})
    List<BlogInfo> getpaper(@Param("author") String author);
}
