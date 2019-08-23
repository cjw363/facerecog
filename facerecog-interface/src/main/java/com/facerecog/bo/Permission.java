package com.facerecog.bo;

import java.util.List;

public class Permission {
    private String permissionId;
    private String permissionName;
    private String url;
    private List<Role> roleList;

    public String getId() {
        return permissionId;
    }

    public void setId(String id) {
        this.permissionId = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
