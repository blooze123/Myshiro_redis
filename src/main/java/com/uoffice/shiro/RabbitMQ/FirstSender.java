package com.uoffice.shiro.RabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FirstSender {
    @Autowired
    AmqpTemplate rabbitTemplate;

    public void send(){
        String context="你好彼得兔！！！";
        System.out.println("彼得兔在发送消息！");
        this.rabbitTemplate.convertAndSend("myRabbit",context);
    }


}
