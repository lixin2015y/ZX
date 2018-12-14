package com.lee.utils;

/**
 * @author lee
 * @version 1.0
 * @date 2018/12/14 19:01
 * @description TODO
 **/
public class Handler implements Runnable {
    public void run() {

    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {

            }
        }).start();
    }
}


