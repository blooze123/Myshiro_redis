package com.uoffice.shiro.controller;

import com.uoffice.shiro.bean.User;
import com.uoffice.shiro.dao.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class testController {
    @Resource
    UserMapper usermapper;
    @Resource
    User user;

    @RequestMapping("/helloMybatis")
    public ModelAndView mybatisTest(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("helloMybatis");
        mav.addObject("hello","shiro");
        user=usermapper.selectAll();
        mav.addObject("user",user);
        return mav;
    }
}
