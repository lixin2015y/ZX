package com.lee.vo;

/**
 * @author lee
 */
public class NewsPageVo extends PageVo{

    private String newstype;

    private String title;

    public NewsPageVo() {
        super();
    }

    public String getNewstype() {
        return newstype;
    }

    public void setNewstype(String newstype) {
        this.newstype = newstype;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
