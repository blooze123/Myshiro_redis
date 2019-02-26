package com.uoffice.shiro.RabbitMQ;

import com.uoffice.shiro.bean.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.user")
public class SecondReceive {

    @RabbitHandler
    public void getUser(User user) throws ClassNotFoundException {
        System.out.println("彼得兔把信送给了"+Class.forName(SecondReceive.class.getName()).toString()+";user是:"+user.getUsername());
    }
}
