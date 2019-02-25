package com.uoffice.shiro.RabbitMQ;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Rabbit配置类
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue Queue(){
        return new Queue("myRabbit");
    }
}
