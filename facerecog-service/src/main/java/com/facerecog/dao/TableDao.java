package com.facerecog.dao;

/**
 * @Classname TableDao
 * @Description
 * @Date 2018/10/22 14:52
 * @Created by cjw
 */
public interface TableDao {
    int selectLastInsertID();

    boolean createTableWarehouse(int wid);
}
