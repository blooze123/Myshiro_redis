package com.uoffice.shiro.service;

import com.uoffice.shiro.bean.*;

import java.util.List;

public interface ILoginService {
    public User findByName(String name);

    List<Role> findRole(UserRole userRole);

    List<Permission> finPermission(RolePermission rolePermission);
}
