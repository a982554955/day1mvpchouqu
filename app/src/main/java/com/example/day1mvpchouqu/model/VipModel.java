package com.example.zhulongstudygroup.model;


import com.example.day1mvpchouqu.constants.Method;

import frame.ApiConfig;
import frame.FrameApplication;
import frame.Host;
import frame.ICommonModel;
import frame.ICommonPresenter;
import frame.NetManger;
import frame.utils.ParamHashMap;

public class VIPModel implements ICommonModel {
        private NetManger mManger = NetManger.getInstance();
    @Override
    public void getData(ICommonPresenter pPresenter, int whichApi, Object[] params) {

        switch (whichApi){

            case ApiConfig.VIP_BANNER:
                //uid=15063681&pro=21&uid=15063681&time=1591368296&
                // devices=oppoR11&system=android,5.1.1&
                // version=2.1.4&unique_id=355757265852349&client_id=205
                ParamHashMap map = new ParamHashMap().add("uid", FrameApplication.getFrameApplication().getLoginInfo().getUid());
                mManger.netWork(NetManger.mService.getVIPBannerBean(Host.VIP_LIST_OPENAPI+ Method.GET_NEW_VIP,map),pPresenter,whichApi,params[0]);
                break;
            case ApiConfig.VIP_LIST:
                //specialty_id=21&sort=2&page=2&uid=15063681&appid=301&time=1591368435&
                // token=a0cccbcac418f78103c754a870494b85&devices=oppoR11&
                // system=android,5.1.1&version=2.1.4&unique_id=355757265852349&client_id=205
                ParamHashMap add = new ParamHashMap().add("specialty_id", FrameApplication.getFrameApplication().getSelectedInfo().getSpecialty_id())
                        .add("page",params[1]);
                mManger.netWork(NetManger.mService.getVIPListBean(Host.VIP_LIST_OPENAPI + Method.GETVIPSMALLLESSONLIST,add),pPresenter,whichApi,params[0]);
                break;
        }
    }
}
