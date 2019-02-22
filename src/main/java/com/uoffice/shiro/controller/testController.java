package com.uoffice.shiro.controller;

import com.uoffice.shiro.bean.User;
import com.uoffice.shiro.dao.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class testController {
    @Resource
    UserMapper usermapper;
    @Resource
    List<User> userList=new ArrayList<>();

    @RequestMapping("/helloMybatis")
    public ModelAndView mybatisTest(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("helloMybatis");
        mav.addObject("hello","shiro");
        userList=usermapper.selectAll();
        mav.addObject("users",userList);
        return mav;
    }
}
