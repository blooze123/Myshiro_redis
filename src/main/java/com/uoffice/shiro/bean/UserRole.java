package com.uoffice.shiro.bean;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class UserRole implements Serializable {
    private Long urId;

    private Long userId;

    private Long roleId;

    public Long getUrId() {
        return urId;
    }

    public void setUrId(Long urId) {
        this.urId = urId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}