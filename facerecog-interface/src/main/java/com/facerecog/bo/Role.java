package com.facerecog.bo;

import java.io.Serializable;

/**
 * @Classname Role
 * @Description
 * @Date 2019/8/22 10:41
 * @Created by cjw
 */
public class Role implements Serializable {
    private int roleId;
    private String roleName;
    private String roleDesc;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}
