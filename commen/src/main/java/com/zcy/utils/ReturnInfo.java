package com.zcy.utils;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by zcy on 2018/8/1.
 */
public class ReturnInfo {
    PageInfo pageinfo;
    boolean flag;
    String info;
    List list;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public PageInfo getPageinfo() {
        return pageinfo;
    }

    public void setPageinfo(PageInfo pageinfo) {
        this.pageinfo = pageinfo;
    }
}
