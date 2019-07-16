package com.facerecog.pojo;


import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Classname Page
 * @Description
 * @Date 2018/12/12 13:33
 * @Created by cjw
 */
public class PageData<T> extends PageInfo<T> {
    public PageData(List<T> list) {
        super(list);
    }

    @Override
    public String toString() {
        return "Page{total=" + this.getTotal() + ", list=" + this.getList() + '}';
    }
}
