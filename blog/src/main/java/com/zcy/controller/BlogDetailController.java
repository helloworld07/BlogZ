package com.zcy.controller;

import com.zcy.domain.BlogInfo;
import com.zcy.service.BlogService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zcy on 2018/8/9.
 */
@Controller
@RequestMapping("/detail")
public class BlogDetailController {

    @Autowired
    private BlogService blogService;

    //文章详情
    @RequestMapping("/page")
    public String getdetail(ModelMap modelMap,@Param("id") String id){
        int idnum = Integer.parseInt(id);
        List<BlogInfo> list = blogService.getpaperdetail(idnum);
        BlogInfo blogInfo = list.get(0);
        String title = blogInfo.getTitle();
        modelMap.addAttribute("pagelist",list);
        return "blogdetail";
    }
}
