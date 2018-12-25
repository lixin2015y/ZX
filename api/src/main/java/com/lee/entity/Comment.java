package com.lee.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lee
 **/
public class Comment implements Serializable {

    private static final long serialVersionUID = -5352580740112012624L;

    private String id;

    private String entityid;

    private String entitytype;

    private String userid;

    private Date creationtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntityid() {
        return entityid;
    }

    public void setEntityid(String entityid) {
        this.entityid = entityid;
    }

    public String getEntitytype() {
        return entitytype;
    }

    public void setEntitytype(String entitytype) {
        this.entitytype = entitytype;
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
        return "Comment{" +
                "id='" + id + '\'' +
                ", entityid='" + entityid + '\'' +
                ", entitytype='" + entitytype + '\'' +
                ", userid='" + userid + '\'' +
                ", creationtime=" + creationtime +
                '}';
    }
}
