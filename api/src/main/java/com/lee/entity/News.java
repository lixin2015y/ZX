package com.lee.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lee
 * @version 1.0
 * @date 2018/12/13 16:55
 * @description
 **/
public class News implements Serializable {

    private static final long serialVersionUID = 9083568856570180698L;

    private String id;

    private String newstype;

    private String title;

    private String content;

    private String pictureurl;

    private String totop;

    private String tofine;

    private String userid;

    @JSONField(format = "yyyy-MM-dd")
    private Date creaiontime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPictureurl() {
        return pictureurl;
    }

    public void setPictureurl(String pictureurl) {
        this.pictureurl = pictureurl;
    }

    public String getTotop() {
        return totop;
    }

    public void setTotop(String totop) {
        this.totop = totop;
    }

    public String getTofine() {
        return tofine;
    }

    public void setTofine(String tofine) {
        this.tofine = tofine;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getCreaiontime() {
        return creaiontime;
    }

    public void setCreaiontime(Date creaiontime) {
        this.creaiontime = creaiontime;
    }

    @Override
    public String toString() {
        return "News{" +
                "id='" + id + '\'' +
                ", newstype='" + newstype + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", pictureurl='" + pictureurl + '\'' +
                ", totop='" + totop + '\'' +
                ", tofine='" + tofine + '\'' +
                ", userid='" + userid + '\'' +
                ", creaiontime=" + creaiontime +
                '}';
    }
}
