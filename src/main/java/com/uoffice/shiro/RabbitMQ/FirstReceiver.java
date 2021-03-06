package com.uoffice.shiro.RabbitMQ;


import com.uoffice.shiro.bean.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "myRabbit")
public class FirstReceiver {

    @RabbitHandler
    public void getMessage(String message) throws ClassNotFoundException {
        System.out.println("彼得兔把信送给了"+Class.forName(FirstReceiver.class.getName()).toString()+";信的内容是:"+message);
    }

}
