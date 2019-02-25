package com.uoffice.shiro.RabbitMQ;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "myRabbit")
public class FirstReceiver {

    @RabbitHandler
    public void getMessage(String message){
        System.out.println("彼得兔把信送到了，信的内容是："+message);
    }
}
