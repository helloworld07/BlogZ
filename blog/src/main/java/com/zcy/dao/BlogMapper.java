package com.zcy.dao;

import com.zcy.domain.BlogInfo;
import com.zcy.domain.ClassifyInfo;
import com.zcy.domain.CommentInfo;
import com.zcy.domain.Userinfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
            "order by pubtime desc",
            "</script>"})
    List<BlogInfo> getpaper(@Param("classify") String classify);

    @Select("select * from paper where flag = '0' and userid = #{userid}")
    List<BlogInfo> mypaper(@Param("userid") int userid);

    @Select("select id,name,icon from classify")
    List<ClassifyInfo> getClassify();

    @Insert("insert into paper (title,content,pubtime,flag,author,userid,classify) values (#{title},#{content},#{pubtime},#{flag},#{author},#{userid},#{classify})")
    int commitBlog(@Param("title") String title, @Param("content") String content, @Param("pubtime") String pubtime, @Param("flag") String flag, @Param("author") String author, @Param("userid") int userid, @Param("classify") String classify);

    @Select("select * from paper where flag = '1' and userid = #{userid}")
    List<BlogInfo> getlockpaper(@Param("userid") int userid);

    @Select("select * from paper where id = #{id}")
    List<BlogInfo> getpaperdetail(@Param("id") int id);

    @Select("select c.*,u.nickname from comment c,usertab u where paperid = #{id} and flag = '1' and c.pubid=u.id and replyid = '0' ORDER BY pubtime DESC")
    List<CommentInfo> getcomment(@Param("id") int id);

    @Insert("insert into comment (paperid,content,pubid,replyid) values (#{paperid},#{content},#{pubid},#{replyid})")
    int addComment(@Param("paperid") int paperid, @Param("content") String paper, @Param("pubid") String pubid, @Param("replyid") int replyid);

    @Update("update comment set flag = '0' where id = #{id}")
    int delComment(@Param("id") int id);

    @Select("select c.*,u.nickname from comment c,usertab u where c.pubid=u.id and replyid = #{replyid} ORDER BY pubtime DESC")
    List<CommentInfo> getreply(@Param("replyid") int replyid);

    @Update("update paper set zan = zan+1 where id = #{id}")
    int addZan(@Param("id") int id);

    @Select("select * from usertab where id = #{id}")
    List<Userinfo> getUserById(@Param("id") int id);

    @Update("update usertab set collids = #{collids} where id = #{id}")
    int updateColl(@Param("collids") String collids,@Param("id")int id);

    @Select("select * from paper where id IN (${ids})")
    List<BlogInfo> getCollById(@Param("ids")String ids);
}
