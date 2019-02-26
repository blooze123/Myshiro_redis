package com.uoffice.shiro.RabbitMQ;

import com.uoffice.shiro.bean.User;
import org.springframework.amqp.core.AmqpTemplate;
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

    public void sendUser(User user) {
        System.out.println("彼得兔正在送一个人:"+user.toString());
        this.rabbitTemplate.convertAndSend("exchange","topic.user",user);
    }

    public void sendUser2(User user) {
        System.out.println("彼得兔正在送一个人:"+user.toString());
        this.rabbitTemplate.convertAndSend("exchange","topic.user2",user);
    }
}
