package com.uoffice.shiro.controller;

import com.uoffice.shiro.RabbitMQ.FirstSender;
import com.uoffice.shiro.bean.User;
import com.uoffice.shiro.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class RabbitController {
    @Autowired
    private FirstSender sender;

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/firstRabbit")
    public String firstRabbit(){
        sender.send();
        return "Rabbit/firstRabbit";
    }

    @RequestMapping("/toTopic")
    public String toTopic(){
        return "Rabbit/toTopic";
    }

    @RequestMapping("/topic")
    public ModelAndView Topic(String username){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("Rabbit/topicMessage");
        User user=userMapper.findByName(username);
        mav.addObject("user",user);
        //发送消息给各个receiver
        for (int i=0;i<20;i++){
            //发送sendUser会匹配到topic.#和topic.user 两个Receiver都可以收到消息。
            sender.sendUser(user);
        }
        //发送sendUser2只能匹配到topic.#
        sender.sendUser2(user);
        return mav;
    }

}
