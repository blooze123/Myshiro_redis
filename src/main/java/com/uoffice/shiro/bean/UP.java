package com.uoffice.shiro.bean;

import org.springframework.stereotype.Repository;

@Repository
public class UP {

    public String aa;

    public String bb;

    public int up_id;

    public User user;

    public Permission permission;

    public void setAa(String aa) {
        this.aa = aa;
    }

    public void setBb(String bb) {
        this.bb = bb;
    }

    public void setUp_id(int up_id) {
        this.up_id = up_id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public String getAa() {
        return aa;
    }

    public String getBb() {
        return bb;
    }

    public int getUp_id() {
        return up_id;
    }

    public User getUser() {
        return user;
    }

    public Permission getPermission() {
        return permission;
    }
}
