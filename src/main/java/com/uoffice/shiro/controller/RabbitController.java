package com.uoffice.shiro.controller;

import com.uoffice.shiro.RabbitMQ.FirstSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RabbitController {
    @Autowired
    private FirstSender sender;

    @RequestMapping("/firstRabbit")
    public String firstRabbit(){
        sender.send();
        return "Rabbit/firstRabbit";
    }
}
