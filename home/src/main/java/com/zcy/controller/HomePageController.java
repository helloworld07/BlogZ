package com.zcy.controller;

import com.zcy.Service.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zcy on 2018/8/9.
 */
@Controller
@RequestMapping("/home")
public class HomePageController {

    @Autowired
    RedisServiceImpl redisService;

    @RequestMapping("/page")
    public String homePage(ModelMap modelMap){
        String content = (String) redisService.get("content");
        modelMap.addAttribute("content",content);
        return "homepage";
    }
}
