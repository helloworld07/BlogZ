package com.zcy.service;

import com.github.pagehelper.PageHelper;
import com.zcy.dao.BlogMapper;
import com.zcy.domain.BlogInfo;
import com.zcy.domain.ClassifyInfo;
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
    public List<BlogInfo> getpaper(String author,int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> list = blogMapper.getpaper(author);
        return list;
    }

    //文章分类
    public List<ClassifyInfo> getClassify(){
        List<ClassifyInfo> list = blogMapper.getClassify();
        return list;
    }

    //提交博客
    public int commitBlog(String title,String content,String pubtime,String flag,String author,String classify){
        int count = blogMapper.commitBlog(title,content,pubtime,flag,author,classify);
        return count;
    }
}
