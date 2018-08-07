package com.zcy.dao;

import com.zcy.domain.BlogInfo;
import com.zcy.domain.ClassifyInfo;
import org.apache.ibatis.annotations.Insert;
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

    @Select("select id,name from classify")
    List<ClassifyInfo> getClassify();

    @Insert("insert into paper (title,content,pubtime,flag,author,classify) values (#{title},#{content},#{pubtime},#{flag},#{author},#{classify})")
    int commitBlog(@Param("title") String title,@Param("content")String content,@Param("pubtime") String pubtime,@Param("flag")String flag,@Param("author")String author,@Param("classify") String classify);
}
