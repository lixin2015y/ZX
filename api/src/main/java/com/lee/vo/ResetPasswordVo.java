package com.lee.vo;

import java.io.Serializable;

/**
 * @author lee
 * @version 1.0
 * @date 2018/12/13 15:38
 * @description TODO
 **/
public class ResetPasswordVo implements Serializable {

    private static final long serialVersionUID = 1101968685986643460L;

    private String oldpassword;

    private String password;

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
