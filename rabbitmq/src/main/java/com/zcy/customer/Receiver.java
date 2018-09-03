package com.zcy.customer;

import com.zcy.domain.Userinfo;
import com.zcy.service.BlogService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zcy on 2018/8/21.
 */
@Component
public class Receiver {
    private final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @RabbitListener(queues = "hello.queue1")
    public String processMessage1(String msg) {
        System.out.println(Thread.currentThread().getName() + " 接收到来自hello.queue1队列的消息：" + msg);
        return msg.toUpperCase();
    }

    /*@RabbitListener(queues = "hello.queue2")
    public void processMessage2(String msg) {
        System.out.println(Thread.currentThread().getName() + " 接收到来自hello.queue2队列的消息：" + msg);
    }*/

    @Autowired
    private BlogService blogService;

    @RabbitListener(queues = "queueForZan")
    public void receiverForZan(String queueForZan) {
        int i = Integer.parseInt(queueForZan);
        int count = blogService.addZan(i);
        if (count==1){
            //System.out.println("add zan successed!");
        }else {
            logger.error("add zan failed!");
        }
    }

    @Autowired
    private JavaMailSender javaMailSender;

    @RabbitListener(queues = "qForEmail")
    public void receiverForEmail(String component){
        //拆分组件
        String VerifiCode = component.substring(0,6);
        String sendTo = component.substring(6,component.length());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("2329986440@qq.com");//官方邮箱地址
        message.setTo(sendTo);
        message.setSubject("BlogZ Verification Code");
        message.setText("Hello, This is a system Email from BlogZ , you Verification Code is \n"+
        VerifiCode+"\n (The verification code is only valid for 5 minutes.)\n If it is not for you, please change the password as soon as possible.");
        javaMailSender.send(message);
    }
}
