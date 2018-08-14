package com.zcy.dao;

import com.zcy.domain.BlogInfo;
import com.zcy.domain.ClassifyInfo;
import com.zcy.domain.CommentInfo;
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
            "<when test='classify!=null'>",
            "AND classify = #{classify}",
            "</when>",
            "and flag = '0'",
            "</script>"})
    List<BlogInfo> getpaper(@Param("classify") String classify);

    @Select("select id,name,icon from classify")
    List<ClassifyInfo> getClassify();

    @Insert("insert into paper (title,content,pubtime,flag,author,userid,classify) values (#{title},#{content},#{pubtime},#{flag},#{author},#{userid},#{classify})")
    int commitBlog(@Param("title") String title, @Param("content") String content, @Param("pubtime") String pubtime, @Param("flag") String flag, @Param("author") String author, @Param("userid") int userid, @Param("classify") String classify);

    @Select("select * from paper where flag = '1' and userid = #{userid}")
    List<BlogInfo> getlockpaper(@Param("userid") int userid);

    @Select("select * from paper where id = #{id}")
    List<BlogInfo> getpaperdetail(@Param("id") int id);

    @Select("select c.*,u.nickname from comment c,usertab u where paperid = #{id} and flag = '1' and c.pubid=u.id ORDER BY pubtime DESC")
    List<CommentInfo> getcomment(@Param("id") int id);

    @Insert("insert into comment (paperid,content,pubid) values (#{paperid},#{content},#{pubid})")
    int addComment(@Param("paperid") int paperid, @Param("content") String paper, @Param("pubid") String pubid);
}
