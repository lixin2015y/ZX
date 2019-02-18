package com.lee.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lee
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 2194210847103624683L;

    private String id;

    private String fromid;

    private String fromtype;

    private String toid;

    private String messagetype;

    private String entityid;

    @JSONField(format = "yyyy-MM-dd HH点mm分")
    private Date time;

    private String deleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    public String getFromtype() {
        return fromtype;
    }

    public void setFromtype(String fromtype) {
        this.fromtype = fromtype;
    }

    public String getToid() {
        return toid;
    }

    public void setToid(String toid) {
        this.toid = toid;
    }

    public String getMessagetype() {
        return messagetype;
    }

    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype;
    }

    public String getEntityid() {
        return entityid;
    }

    public void setEntityid(String entityid) {
        this.entityid = entityid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", fromid='" + fromid + '\'' +
                ", fromtype='" + fromtype + '\'' +
                ", toid='" + toid + '\'' +
                ", messagetype='" + messagetype + '\'' +
                ", entityid='" + entityid + '\'' +
                ", time=" + time +
                ", deleted='" + deleted + '\'' +
                '}';
    }
}
