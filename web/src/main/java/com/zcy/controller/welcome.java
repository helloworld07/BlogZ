package com.zcy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zcy on 2018/7/31.
 */
@Controller
public class welcome {

    @RequestMapping("/")
    public String welcome() {
        return "welcome";
    }
}
