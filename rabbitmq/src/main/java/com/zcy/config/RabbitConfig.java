package com.zcy.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zcy on 2018/8/21.
 */
@Configuration
public class RabbitConfig {
    //声明队列
    @Bean
    public Queue queue1() {
        return new Queue("hello.queue1", true); // true表示持久化该队列
    }

    @Bean
    public Queue queueForZan(){
        return new Queue("queueForZan");
    }

    @Bean
    public Queue quereForEmail(){
        return new Queue("qForEmail");
    }
    /*@Bean
    public Queue queue2() {
        return new Queue("hello.queue2", true);
    }*/

    //声明交互器
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    //绑定
    @Bean
    public Binding binding1() {
        //将queue1绑定进名为topicExchange的Exchange，命名Routing key为key.1
        return BindingBuilder.bind(queue1()).to(topicExchange()).with("key.1");
    }

   /* @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queue2()).to(topicExchange()).with("key.#");
    }*/
}
