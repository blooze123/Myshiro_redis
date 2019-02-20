package com.uoffice.shiro.controller;

import com.uoffice.shiro.bean.User;
import com.uoffice.shiro.dao.UserMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Controller
public class RedisController {
    @Resource(name="redisTemplate")
    private RedisTemplate redisTemplate;

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/redis")
    public String testRedis(){
        return "TestRedis/testRedis";
    }

    @RequestMapping("/selectUser")
    public ModelAndView selectUser(String selectUser){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("TestRedis/selectResult");
        //判断是否存在该key
        boolean isKey=redisTemplate.hasKey(selectUser);
        System.out.println(isKey);
        //存在key，则直接从redis缓存中取出value；不存在key，则查询数据库，设置key、value。
        if(isKey){
            System.out.println("进入取redis缓存的判断");
            User user=(User) redisTemplate.opsForValue().get(selectUser);
            mav.addObject("user",user);
            return mav;
        }else{
            System.out.println("进入设置redis缓存判断");
            User user=userMapper.findByName(selectUser);
            redisTemplate.opsForValue().set(selectUser,user,6000, TimeUnit.SECONDS);
            mav.addObject("user",user);
            return mav;
        }
    }
    @RequestMapping("/updateUser")
    public ModelAndView addUser(String username,String newUsername){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("TestRedis/updateUser");
        boolean isKey=redisTemplate.hasKey(username);
        //获取登陆的用户信息，修改用户名，重新更新到数据库中。
        User user=userMapper.findByName(username);
        user.setUsername(newUsername);
        System.out.println(user.getUserId()+","+user.getUsername()+","+user.getSalt());
        boolean result=userMapper.updateUsername(user);
        //存在key，则删除该map,将新的map加进去；不存在key，则直接设置key、value。
        if(isKey){
            redisTemplate.delete(username);
            redisTemplate.opsForValue().set(newUsername,user);
            user=(User)redisTemplate.opsForValue().get(newUsername);
        }else{
            redisTemplate.opsForValue().set(newUsername,user);
            user=(User)redisTemplate.opsForValue().get(newUsername);
        }
        mav.addObject("user",user);
        return mav;
    }


}
