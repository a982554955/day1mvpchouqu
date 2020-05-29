package com.example.day1mvpchouqu.model;


import com.example.frame.ICommonModel;
import com.example.frame.ICommonPresenter;

import com.example.frame.NetManger;

import java.util.Map;



public class TestModel implements ICommonModel {
    String aaa;
    NetManger netManger=NetManger.getInstance();
    @Override
    public void getData(final ICommonPresenter iCommonPresenter, final int whichApi, Object[] params) {

            final int loadType= (int) params[0];
            Map map= (Map) params[1];
            int pageId= (int) params[2];
            netManger.netWork(netManger.getService().getTestData(map,pageId),iCommonPresenter,whichApi,loadType);
      }
    }
//    @Override
//    public void getTrendsDate(ICommonPresenter iCommonPresenter, int whichApi, int dataType, Object[] params) {
//
//    }

