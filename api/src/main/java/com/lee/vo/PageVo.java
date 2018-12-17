package com.lee.vo;

import java.io.Serializable;

/**
 * @author lee
 * @version 1.0
 * @date 2018/12/17 10:41
 * @description TODO
 **/
public class PageVo implements Serializable {

    private static final long serialVersionUID = 5217142602944813830L;

    private Integer pageSize;

    private Integer pageNum;

    private Integer begin;

    public PageVo() {
    }

    public PageVo(Integer pageSize, Integer pageNum) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void initPage() {
        this.begin = (this.pageNum - 1) * this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getBegin() {
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }
}
