package com.uoffice.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String Login(String username,String password){
        System.out.println("用户名为："+username+",,密码为"+password);
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                username,
                password);

        //进行验证，这里可以捕获异常，然后返回对应信息
        subject.login(usernamePasswordToken);
        return "index";
    }
    @RequestMapping("/index")
    public ModelAndView toIndex(){
        System.out.println("进入index方法");
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

//    public static void main(String[] args) {
//        Md5Hash md5=new Md5Hash("aa","blooze");
//        System.out.println(md5.toString());
//    }
}
