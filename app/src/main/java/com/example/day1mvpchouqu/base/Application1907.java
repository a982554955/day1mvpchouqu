package com.example.day1mvpchouqu.base;

import android.content.Context;

import com.example.frame.FrameApplication;

public class Application1907 extends FrameApplication {
    private static Application1907 mApplication1907;
    private static Context mApplicationContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication1907=this;
    }

    public Application1907 getApplication(){

        return mApplication1907;
    }
    public Context get07ApplicationContext(){
        return mApplicationContext.getApplicationContext();
    }
}
