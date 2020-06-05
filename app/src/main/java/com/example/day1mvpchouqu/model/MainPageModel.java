package com.example.day1mvpchouqu.model;

import java.util.HashMap;

import frame.ICommonModel;
import frame.ICommonPresenter;
import frame.NetManger;

/**
 * Created by 任小龙 on 2020/6/4.
 */
public class MainPageModel implements ICommonModel {
    private NetManger manager = NetManger.getInstance();

    @Override
    public void getData(ICommonPresenter pPresenter, int whichApi, Object[] params) {

    }
}
