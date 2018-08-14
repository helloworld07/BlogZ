package com.zcy.service;

import com.github.pagehelper.PageHelper;
import com.zcy.dao.BlogMapper;
import com.zcy.domain.BlogInfo;
import com.zcy.domain.ClassifyInfo;
import com.zcy.domain.CommentInfo;
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
    public List<BlogInfo> getpaper(String classify,int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> list = blogMapper.getpaper(classify);
        return list;
    }

    //文章分类
    public List<ClassifyInfo> getClassify(){
        List<ClassifyInfo> list = blogMapper.getClassify();
        return list;
    }

    //提交博客
    public int commitBlog(String title,String content,String pubtime,String flag,String author,int userid,String classify){
        int count = blogMapper.commitBlog(title,content,pubtime,flag,author,userid,classify);
        return count;
    }

    //私密文章
    public List<BlogInfo> getlockpaper(int userid,int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> list = blogMapper.getlockpaper(userid);
        return list;
    }

    //获取文章详情
    public List<BlogInfo> getpaperdetail(int id){
        List<BlogInfo> list = blogMapper.getpaperdetail(id);
        return list;
    }

    //获取文章评论
    public List<CommentInfo> getcomment(int id,int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<CommentInfo> list = blogMapper.getcomment(id);
        return list;
    }

    //新增评论
    public int addComment(int paperid ,String content, String pubid){
        int count = blogMapper.addComment(paperid, content, pubid);
        return count;
    }
}
