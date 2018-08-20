package com.zcy.pipline;

import com.zcy.dao.LoginMapper;
import com.zcy.domain.Userinfo;
import com.zcy.service.RedisServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcy on 2018/8/20.
 */
@Component
public class ContentPipline implements Pipeline {

    @Resource
    private RedisServiceImpl redisService;

    private final Logger logger = LoggerFactory.getLogger(ContentPipline.class);

    @Override
    public void process(ResultItems resultItems, Task task) {
        //取数据
        ArrayList title = resultItems.get("title");
        ArrayList content = resultItems.get("content");
        //存入redis
        boolean flag1 = redisService.set("title", title);
        boolean flag2 = redisService.set("content", content);

        if (flag1&&flag2) {
            logger.info("redis save content successed!");
        } else {
            logger.error("redis save failed!");
        }
    }
}
