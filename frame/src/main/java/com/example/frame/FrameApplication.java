package com.example.frame;

import android.app.Application;
import android.content.Context;

import com.example.data.Device;
import com.example.data.LoginInfo;


public class FrameApplication extends Application {
    private static FrameApplication application;
    private Device mDeviceInfo;
    private LoginInfo mLoginInfo;
    private String cookie;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public LoginInfo getLoginInfo() {
        return mLoginInfo;
    }

    public void setLoginInfo(LoginInfo mLoginInfo) {
        this.mLoginInfo = mLoginInfo;
    }

    public Device getDeviceInfo() {
        return mDeviceInfo;
    }

    public void setDeviceInfo(Device mDeviceInfo) {
        this.mDeviceInfo = mDeviceInfo;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static FrameApplication getFrameApplication(){
        return application;
    }

    public static Context getFrameApplicationContext(){
        return application.getApplicationContext();
    }
}
