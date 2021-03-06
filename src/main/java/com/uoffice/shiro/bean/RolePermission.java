package com.uoffice.shiro.bean;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class RolePermission implements Serializable {
    private Long rpId;

    private Long roleId;

    private Long permissionId;

    public Long getRpId() {
        return rpId;
    }

    public void setRpId(Long rpId) {
        this.rpId = rpId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}