package com.lee.constant;

/**
 * @author lee
 */
public class ZxException extends Exception {

    private int code;

    private String message;

    public ZxException(String message) {
        super(message);
    }

    public ZxException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
