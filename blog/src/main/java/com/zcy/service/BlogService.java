package com.zcy.service;

import com.github.pagehelper.PageHelper;
import com.zcy.dao.BlogMapper;
import com.zcy.domain.BlogInfo;
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

    public List<BlogInfo> getpaper(String author,int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> list = blogMapper.getpaper(author);
        return list;
    }
}
