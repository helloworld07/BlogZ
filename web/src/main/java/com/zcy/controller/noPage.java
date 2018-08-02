package com.zcy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zcy on 2018/7/31.
 */
@Controller
public class noPage {
    @RequestMapping("/nopage")
    public String nopage(){
        return "nopage";
    }
}
