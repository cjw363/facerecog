package com.facerecog.dao;

import com.facerecog.bo.Permission;
import com.facerecog.bo.Role;
import com.facerecog.bo.UserInfo;
import com.facerecog.pojo.ParamData;

import java.util.List;

/**
 * @Classname UserDao
 * @Description
 * @Date 2018/10/16 14:50
 * @Created by cjw
 */
public interface UserDao {
    UserInfo selectUserMapByName(ParamData pd);

    boolean insertUser(ParamData pd);

    int selectWidByUserID(String userId);

    void updateUserLoginTime(ParamData pd);

    boolean updateUserPassword(ParamData pd);

    List<ParamData> selectWidList();

    List<Role> selectRoleListByUserID(int userId);

    List<Permission> selectPermissionListMap();

    List<Role> selectRoleListByPermisID(int permissionId);

    List<String> selectRoleNameListByUrl(String url);
}
