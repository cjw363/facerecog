package com.facerecog.pojo;


import com.github.pagehelper.Page;
import java.io.Serializable;
import java.util.List;

/**
 * @Classname PageData
 * @Description
 * @Date 2018/12/12 13:33
 * @Created by cjw
 */
public class PageData<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    protected long total;
    protected List<T> list;

    public PageData() {
    }

    public PageData(List<T> list) {
        this.list = list;
        if (list instanceof Page) {
            this.total = ((Page) list).getTotal();
        } else {
            this.total = (long) list.size();
        }

    }

    public static <T> PageData<T> of(List<T> list) {
        return new PageData(list);
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public String toString() {
        return "PageData{total=" + this.total + ", list=" + this.list + '}';
    }
}