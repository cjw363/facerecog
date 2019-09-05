package com.facerecog.service.impl;

import com.facerecog.dao.UserDao;
import com.facerecog.pojo.ParamData;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Classname UserSecurityServiceImpl
 * @Description
 * @Date 2019/8/22 10:50
 * @Created by cjw
 */
@Service
public class UserSecurityServiceImpl implements UserDetailsService {
    @Resource
    private UserDao mUserDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        ParamData pd = new ParamData<>();
        pd.put("name", userName);
        return mUserDao.selectUserMapByName(pd);
    }
}
