package com.zcy.webmagic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Date;
import java.util.List;

/**
 * Created by zcy on 2018/8/18.
 */
@Component
public class WebMagic implements PageProcessor {
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    private final Logger logger = LoggerFactory.getLogger(WebMagic.class);
    /*每分钟爬去数据存入缓存*/
    @Override
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
        //用于获取所有满足这个正则表达式的链接
        List<String> links = page.getHtml().links().regex("https://blog\\.csdn\\.net/").all();
        //将这些链接加入到待抓取的队列中去
        page.addTargetRequests(links);
        //相同元素的结果加到相应的集合中去
        //全文，比较杂乱，不好去除不想要的内容
        //page.putField("model", page.getHtml().xpath("//ul[@class='feedlist_mod home']").toString());
        page.putField("title", page.getHtml().xpath("//div[@class='title']/h2").all());
        page.putField("content", page.getHtml().xpath("//div[@class='summary oneline']").all());
        /*page.putField("titleList", page.getHtml().xpath("//div[@class='title']/h2/a/text()").all());
        page.putField("contentlist", page.getHtml().xpath("//span[@class='content']/text()").all());
        page.putField("pinlunlist", page.getHtml().xpath("//span[@class='link_comments']/text()").all());*/
    }

    @Override
    public Site getSite() {
        return site;
    }

    @Autowired
    private ContentPipline contentPipline;

    //每5分钟更新一次数据
    @Scheduled(fixedRate = 1000*60*5)
    //@Scheduled(cron="* 0/5 * * * ? ")
    public void ScheMagic() {
        logger.info("Scheduled Running Time is:"+ new Date() );
        Spider spider = Spider.create(new WebMagic()).addUrl("https://blog.csdn.net").addPipeline(contentPipline).thread(5);
        spider.start();
        spider.stop();
        //Spider.create(new WebMagic()).addUrl("https://blog.csdn.net").addPipeline(new ConsolePipeline()).run();
        //Spider.create(new WebMagic()).addUrl("https://blog.csdn.net").addPipeline(new ContentPipline()).run();
    }
}
