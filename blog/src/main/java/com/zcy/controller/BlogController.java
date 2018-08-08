package com.zcy.controller;

import com.github.pagehelper.PageInfo;
import com.zcy.domain.BlogInfo;
import com.zcy.domain.ClassifyInfo;
import com.zcy.domain.Userinfo;
import com.zcy.service.BlogService;
import com.zcy.utils.ReturnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by zcy on 2018/8/4.
 */
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    //获取文章列表
    @RequestMapping("/getpaper")
    public ReturnInfo getpaper(ReturnInfo r, HttpServletRequest request, ModelMap modelMap, int pageNum, int pageSize) {
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

    //获取文章分类
    @RequestMapping("/getclassify")
    public ReturnInfo getClassify(ReturnInfo r) {
        List<ClassifyInfo> list = blogService.getClassify();
        r.setList(list);
        return r;
    }

    //提交博客
    @RequestMapping("/commmitblog")
    public ReturnInfo commitBlog(ReturnInfo r, String title, String content, String flag, String classify, HttpServletRequest request) {
        Userinfo userinfo = (Userinfo) request.getSession().getAttribute("userinfo");
        if (userinfo == null) {
            r.setFlag(false);
            r.setInfo("您还没有登录，请登录后再提交！");
            return r;
        }
        String author = userinfo.getNickname();
        String pubtime = LocalDateTime.now().toString();
        int count = blogService.commitBlog(title, content, pubtime, flag, author, classify);
        if (count < 1) {
            r.setFlag(false);
            r.setInfo("保存博客失败，请重新提交！");
            return r;
        }
        r.setFlag(true);
        r.setInfo("保存成功！");
        return r;
    }


    @RequestMapping(value = "/ueditor")
    public String ueditor(HttpServletRequest request) {
        return "/static/ueditor/jsp/config.json";
    }
}
