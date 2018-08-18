package com.zcy.controller;

import com.zcy.Service.RedisServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.Set;

/**
 * Created by zcy on 2018/8/18.
 */
@Component
public class WebMagic implements PageProcessor {

    @Autowired
    private RedisServiceImpl redisService;

    private final Logger logger = LoggerFactory.getLogger(WebMagic.class);
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    /*每分钟爬去数据存入缓存*/
    @Override
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
        //用于获取所有满足这个正则表达式的链接
        List<String> links = page.getHtml().links().regex("https://blog\\.csdn\\.net/").all();
        //将这些链接加入到待抓取的队列中去
        page.addTargetRequests(links);
        //相同元素的结果加到相应的集合中去
        page.putField("model", page.getHtml().xpath("//ul[@class='feedlist_mod home']").toString());
        String model = page.getResultItems().get("model");
//        System.out.println(model);
        //写入缓存
        boolean flag = Setredis("content", model);
        if (flag) {
            logger.info("redis save content successed!");
        } else {
            logger.error("redis save failed!");
        /*page.putField("titleList", page.getHtml().xpath("//div[@class='title']/h2/a/text()").all());
        page.putField("contentlist", page.getHtml().xpath("//span[@class='content']/text()").all());
        page.putField("pinlunlist", page.getHtml().xpath("//span[@class='link_comments']/text()").all());*/
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    //写入redis
    public boolean Setredis(String key, Object value) {
        boolean set = redisService.set(key, value);
        return set;
    }

    @Scheduled(fixedRate = 60000)
    public void ScheMagic() {
        Spider.create(new WebMagic()).addUrl("https://blog.csdn.net").run();
    }
}
