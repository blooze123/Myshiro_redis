package com.uoffice.shiro.controller;

import com.uoffice.shiro.bean.User;
import com.uoffice.shiro.dao.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Resource
    UserMapper userMapper;

    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String Login(String username, String password){
        System.out.println("用户名为："+username+",,密码为"+password);
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                username,
                password);
        //进行验证，这里可以捕获异常，然后返回对应信息
        subject.login(usernamePasswordToken);
        return "mainFrame";
    }
    @RequestMapping("/index")
    public ModelAndView toIndex(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    //登出
    @RequestMapping(value = "/logout")
    public String logout(){
        System.out.println("进入logout方法");
        return "logout";
    }

    @RequestMapping("/test2")
    public ModelAndView test2(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("testShiro权限");
        Subject subject=SecurityUtils.getSubject();
        subject.checkRole("admin");
        subject.checkPermission("select");
        return mav;
    }
}
