package com.uoffice.shiro.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uoffice.shiro.bean.User;
import com.uoffice.shiro.dao.UserMapper;
import com.uoffice.shiro.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LayuiTestController {
    @Resource
    private UserMapper userMapper;

    List<User> list=new ArrayList<>();

    @RequestMapping("/layui1")
    public String layui1(){
        return "layui/layui1";
    }
    @RequestMapping("/lform")
    public String lform(){
        return "layui/表单及表单控件";
    }
    @RequestMapping("/nav")
    public String nav(){
        return "layui/导航";
    }
    @RequestMapping("/tab")
    public String tab(){
        return "layui/选项卡";
    }
    @RequestMapping("/panel")
    public String panel(){
        return "layui/面板";
    }
    @RequestMapping("/dot")
    public String dot(){
        return "layui/徽章";
    }
    @RequestMapping("/timeline")
    public String timeline(){
        return "layui/时间线";
    }
    @RequestMapping("/layer")
    public String layer(){
        return "layui/layer的运用";
    }
    @RequestMapping("/date")
    public String date(Model model){
        model.addAttribute("Mydate","date");
        return "layui/日期选择器与thymeleaf如何传值到js";
    }
    //分页插件
    @RequestMapping("/page")
    public String page(Model model,@RequestParam(defaultValue ="1",required = true,value = "pageNo") Integer pageNo){
        System.out.println("前台传过来的pageNo:"+pageNo);
        PageHelper.startPage(pageNo,3);
        list=userMapper.selectAll();
        PageInfo<User> page=new PageInfo<User>(list);
        System.out.println("当前页为:"+page.getPageNum());
        model.addAttribute("userList",page);
        model.addAttribute("total",page.getTotal());
        return "layui/分页插件";
    }
    @RequestMapping("/table")
    public String table(){
        return "layui/表格";
    }
    //表格渲染
    @ResponseBody
    @RequestMapping("/jsontable")
    public Map<String,Object> jsonTable(@RequestParam(defaultValue ="1",required = true,value = "page") Integer page,int limit){
        //注意！使用layui的表格自动渲染时，只要前端有设置page、limit这两个属性，则后端一定能够获取到page、limit这两个参数。
        PageHelper.startPage(page,limit);
        list=userMapper.selectAll();
        PageInfo<User> pageList=new PageInfo<User>(list);
        //注意：如果要成功在前端渲染表格，则一定要使用map来存储list数据，以及code，msg，count，data这些基本参数
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "");
        result.put("count",pageList.getTotal());
        result.put("data",list);
       return result;
    }
    //滑块
    @RequestMapping("/hua")
    public String hua(){
        return "layui/滑块";
    }
    //颜色选择器
    @RequestMapping("/col")
    public String col(){
        return "layui/颜色选择器";
    }

    @RequestMapping("/file")
    public String fileLoad(){
        return "layui/文件上传";
    }

    @ResponseBody
    @RequestMapping("/uploadFile")
    public int uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception{
        //获取本项目内部的源路径
        String path= ResourceUtils.getURL("classpath:").getPath();
        System.out.println(path);
        //处理图片的方法，入参为项目源路径和图片文件，并返回图片路径。
        String imagepath= ImageService.imageSave(file,path);
        System.out.println("上传成功，存储图片名为："+imagepath);

        return 1;
    }


}
