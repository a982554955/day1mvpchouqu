package com.example.day1mvpchouqu.model;

import java.util.Map;

import frame.ApiConfig;
import frame.ICommonModel;
import frame.ICommonPresenter;
import frame.NetManger;

//建议：一个独立单元使用一个model，比如说账号注册、验证码注册、账号登录、验证码登录、三方登录
public class TestModel implements ICommonModel {
    NetManger mManger = NetManger.getInstance();

    @Override
    public void getData(final ICommonPresenter pPresenter, final int whichApi, Object[] params) {
        switch (whichApi) {
            case ApiConfig.TEST_GET:
                mManger.netWork(mManger.getService().getTestData((Map) params[1], (int) params[2]), pPresenter, whichApi, (int) params[0]);
                break;
        }
    }


  /*  @Override
    public void getTrendsData(ICommonPresenter pPresenter, int whichApi, int dataType, Object[] param) {

    }*/
}
