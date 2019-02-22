package com.uoffice.shiro.shiro;

import com.uoffice.shiro.bean.*;
import com.uoffice.shiro.service.ILoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

//实现AuthorizingRealm接口用户用户认证
public class MyShiroRealm extends AuthorizingRealm {

    //用于用户查询
    @Resource(name="loginServiceImpl")
    private ILoginService loginService;
    @Resource
    private RolePermission rolePermission;
    @Resource
    private UserRole userRole;

    List<Role> roleList=new ArrayList<>();

    List<Permission> permissionList=new ArrayList<>();

    //角色权限和对应权限添加
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name= (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称
        User user = loginService.findByName(name);
        //根据userId找到用户拥有的所有角色
        userRole.setUserId(user.getUserId());
        roleList=loginService.findRole(userRole);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role:roleList) {
            System.out.println("该用户的角色为："+role.getServerName()+"  ");
            //添加角色
            simpleAuthorizationInfo.addRole(role.getServerName());
            //根据角色ID获得该角色所拥有的所有权限
            rolePermission.setRoleId(role.getRoleId());
            permissionList=loginService.finPermission(rolePermission);
            for (Permission permission:permissionList) {
                System.out.println("该角色的权限为："+permission.getName()+"  ");
                //添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getName());
            }
        }
        return simpleAuthorizationInfo;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        User user = loginService.findByName(name);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            System.out.println("进入doGetAuthenticationInfo方法认证登陆！");
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassword().toString(), getName());
            //对密码进行加盐
            simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("blooze"));
            return simpleAuthenticationInfo;
        }
    }
}
