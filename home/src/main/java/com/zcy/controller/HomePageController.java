package com.zcy.controller;

import com.zcy.service.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        ArrayList title = (ArrayList) redisService.get("title");
        ArrayList content = (ArrayList) redisService.get("content");
        //合并输出
        List<Map<String,String>> columns=new ArrayList<Map<String,String>>();
        for(int i=0;i<title.size();i++){
            Map<String,String> col=new HashMap<>();
            col.put("title", (String) title.get(i));
            col.put("content", (String) content.get(i));
            columns.add(col);
        }
        modelMap.addAttribute("listmap",columns);
        return "homepage";
    }
}
