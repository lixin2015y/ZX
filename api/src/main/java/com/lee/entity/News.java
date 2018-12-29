package com.lee.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lee
 **/
public class News implements Serializable {

    private static final long serialVersionUID = 9083568856570180698L;

    private String id;

    private String newstype;

    private String title;

    private String content;

    private String totop;

    private String tofine;

    private String userid;

    @JSONField(format = "yyyy-MM-dd HH点mm分")
    private Date creationtime;


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

    public Date getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Date creationtime) {
        this.creationtime = creationtime;
    }

    @Override
    public String toString() {
        return "News{" +
                "id='" + id + '\'' +
                ", newstype='" + newstype + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", totop='" + totop + '\'' +
                ", tofine='" + tofine + '\'' +
                ", userid='" + userid + '\'' +
                ", creaiontime=" + creationtime +
                '}';
    }
}
