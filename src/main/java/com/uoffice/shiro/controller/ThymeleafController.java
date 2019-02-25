package com.uoffice.shiro.controller;

import com.uoffice.shiro.bean.User;
import com.uoffice.shiro.dao.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ThymeleafController {
    @Resource
    UserMapper userMapper;
    @RequestMapping("/thf")
    public ModelAndView toThymeleaf(HttpSession session){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("thymeleaf");
        List<User> userList=userMapper.selectAll();
        User user=(User)session.getAttribute("user");
        mav.addObject("userList",userList);
        mav.addObject("myUser",user);
        return mav;
    }
}
