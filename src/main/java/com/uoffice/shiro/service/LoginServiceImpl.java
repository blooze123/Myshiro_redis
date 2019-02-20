package com.uoffice.shiro.service;

import com.uoffice.shiro.bean.*;
import com.uoffice.shiro.dao.PermissionMapper;
import com.uoffice.shiro.dao.RoleMapper;
import com.uoffice.shiro.dao.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("loginServiceImpl")
@Transactional
public class LoginServiceImpl implements ILoginService{
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;
    List<Role> roleList=new ArrayList<>();

    List<Permission> permissionList=new ArrayList<>();

    @Override
    public User findByName(String name) {
        User user=userMapper.findByName(name);
        return user;
    }

    @Override
    public List<Role> findRole(UserRole userRole) {
        roleList=roleMapper.findRole(userRole);
        return roleList;
    }

    @Override
    public List<Permission> finPermission(RolePermission rolePermission) {
        permissionList=permissionMapper.finPermission(rolePermission);
        return permissionList;
    }


}
