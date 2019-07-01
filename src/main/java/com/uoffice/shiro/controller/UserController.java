package com.uoffice.shiro.controller;

import com.uoffice.shiro.bean.UP;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    public UP up;

    @RequestMapping("/toUP")
    public String toUP(){
        return "UP";
    }

    @RequestMapping("/testUP")
    public String testUP(UP up){
        System.out.println(up);
        return "login";
    }
}

