package com.example.day1mvpchouqu.model;


import com.example.frame.ICommonModel;
import com.example.frame.ICommonPresenter;

import com.example.frame.NetManger;

import java.util.Map;


//建议：一个独立单元使用一个Model,比如说账号注册，验证码注册，账号登录，验证码登录，三方登录
public class TestModel implements ICommonModel {
    String aaa;
    NetManger netManger=NetManger.getInstance();
    @Override
    public void getData(final ICommonPresenter iCommonPresenter, final int whichApi, Object[] params) {
            netManger.netWork(netManger.getService().getTestData(
                    (Map) params[1],
                    (int) params[2]),
                    iCommonPresenter,
                    whichApi,
                    (int) params[0]);
      }
    }
//    @Override
//    public void getTrendsDate(ICommonPresenter iCommonPresenter, int whichApi, int dataType, Object[] params) {
//
//    }

