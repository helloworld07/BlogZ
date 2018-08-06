package com.zcy.controller;

import com.github.pagehelper.PageInfo;
import com.zcy.domain.BlogInfo;
import com.zcy.domain.Userinfo;
import com.zcy.service.BlogService;
import com.zcy.utils.ReturnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zcy on 2018/8/4.
 */
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping("/getpaper")
    public ReturnInfo getpaper(ReturnInfo r, HttpServletRequest request, ModelMap modelMap,int pageNum,int pageSize){
        Userinfo userinfo = (Userinfo) request.getSession().getAttribute("userinfo");
        /*if (userinfo!=null) {
            String author = userinfo.getUsername();
            List<BlogInfo> list = blogService.getpaper(author, pageNum, pageSize);
            PageInfo<BlogInfo> pageInfo = new PageInfo<>(list);
            r.setPageinfo(pageInfo);
            r.setList(list);
        }else {
            String author = null;
            List<BlogInfo> list = blogService.getpaper(author, pageNum, pageSize);
            PageInfo<BlogInfo> pageInfo = new PageInfo<>(list);
            r.setPageinfo(pageInfo);
            r.setList(list);
        }*/
        //暂时在博客列表不用用户信息
        String author = null;
        List<BlogInfo> list = blogService.getpaper(author, pageNum, pageSize);
        PageInfo<BlogInfo> pageInfo = new PageInfo<>(list);
        r.setPageinfo(pageInfo);
        r.setList(list);
        return r;
    }
}
