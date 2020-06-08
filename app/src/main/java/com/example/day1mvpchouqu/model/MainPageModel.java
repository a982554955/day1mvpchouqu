package com.example.day1mvpchouqu.model;

import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.constants.Method;

import java.util.HashMap;

import frame.ApiConfig;
import frame.FrameApplication;
import frame.Host;
import frame.ICommonModel;
import frame.ICommonPresenter;
import frame.NetManger;
import frame.constants.Constants;
import frame.utils.ParamHashMap;

/**
 * Created by 任小龙 on 2020/6/4.
 */
public class MainPageModel implements ICommonModel {
    private NetManger manager = NetManger.getInstance();

    @Override
    public void getData(ICommonPresenter pPresenter, int whichApi, Object[] params) {
        switch (whichApi){
            case ApiConfig.MAIN_PAGE_LIST:
                ParamHashMap map = new ParamHashMap().add("specialty_id", FrameApplication.getFrameApplication().getSelectedInfo().getSpecialty_id()).add("page", params[1]).add("limit", Constants.LIMIT_NUM).add("new_banner", 1);
                manager.netWork(NetManger.mService.getCommonList(Host.EDU_OPENAPI+Method.GETINDEXCOMMEND,map),pPresenter,whichApi,params[0]);
                break;
            case ApiConfig.BANNER_LIVE_DATA:
                ParamHashMap live = new ParamHashMap().add("pro", FrameApplication.getFrameApplication().getSelectedInfo().getSpecialty_id()).add("more_live","1").add("is_new",1).add("new_banner",1);
                manager.netWork(NetManger.mService.getBannerLive(Host.EDU_OPENAPI+Method.GETCAROUSELPHOTO,live),pPresenter,whichApi,params[0]);
                break;
        }
    }
}
