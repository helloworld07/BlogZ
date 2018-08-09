package com.zcy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zcy on 2018/8/9.
 */
@Controller
@RequestMapping("/home")
public class HomePageController {
    @RequestMapping("/page")
    public String homePage(){
        return "homepage";
    }
}
