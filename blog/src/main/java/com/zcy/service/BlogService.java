package com.zcy.service;

import com.github.pagehelper.PageHelper;
import com.zcy.dao.BlogMapper;
import com.zcy.domain.BlogInfo;
import com.zcy.domain.ClassifyInfo;
import com.zcy.domain.CommentInfo;
import com.zcy.domain.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zcy on 2018/8/4.
 */
@Service
public class BlogService {
    @Autowired
    private BlogMapper blogMapper;

    //文章列表
    public List<BlogInfo> getpaper(String classify, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> list = blogMapper.getpaper(classify);
        return list;
    }

    //文章分类
    public List<ClassifyInfo> getClassify() {
        List<ClassifyInfo> list = blogMapper.getClassify();
        return list;
    }

    //提交博客
    public int commitBlog(String title, String content, String pubtime, String flag, String author, int userid, String classify) {
        int count = blogMapper.commitBlog(title, content, pubtime, flag, author, userid, classify);
        return count;
    }

    //私密文章
    public List<BlogInfo> getlockpaper(int userid, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> list = blogMapper.getlockpaper(userid);
        return list;
    }

    //获取文章详情
    public List<BlogInfo> getpaperdetail(int id) {
        List<BlogInfo> list = blogMapper.getpaperdetail(id);
        return list;
    }

    //获取文章评论
    public List<CommentInfo> getcomment(int id, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CommentInfo> list = blogMapper.getcomment(id);
        return list;
    }

    //新增评论
    public int addComment(int paperid, String content, String pubid, int commentid) {
        int count = blogMapper.addComment(paperid, content, pubid, commentid);
        return count;
    }

    //我的博客
    public List<BlogInfo> mypaper(int userid, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> list = blogMapper.mypaper(userid);
        return list;
    }

    //删除评论
    public int delComment(int id) {
        int i = blogMapper.delComment(id);
        return i;
    }

    //评论回复
    public List<CommentInfo> getreply(int replyid) {
        List<CommentInfo> list = blogMapper.getreply(replyid);
        return list;
    }

    //赞
    public int addZan(int id) {
        int i = blogMapper.addZan(id);
        return i;
    }

    //获取用户信息
    public List<Userinfo> getUserById(int id){
        List<Userinfo> userById = blogMapper.getUserById(id);
        return userById;
    }

    //更新收藏
    public int updateColl(String collids,int id){
        int i = blogMapper.updateColl(collids, id);
        return i;
    }

    //查看收藏
    public List<BlogInfo> getCollById(String ids){
        List<BlogInfo> collById = blogMapper.getCollById(ids);
        return collById;
    }
}
