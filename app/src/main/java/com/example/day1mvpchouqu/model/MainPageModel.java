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
                ////vip banner+vipzhibo
                //	https://edu.zhulong.com/openapi/lesson/get_new_vip?uid=15063681&pro=21&uid=15063681&time=1591368296&devices=oppoR11&system=android,5.1.1&version=2.1.4&unique_id=355757265852349&client_id=205
                //
                //	//vip recyclerview接口（post）
                //	https://edu.zhulong.com/openapi/lesson/getVipSmallLessonList?<specialty_id=21&sort=2&page=2&uid=15063681&appid=301&time=1591368435&token=a0cccbcac418f78103c754a870494b85&devices=oppoR11&system=android,5.1.1&version=2.1.4&unique_id=355757265852349&client_id=205>
                ParamHashMap live = new ParamHashMap().add("pro", FrameApplication.getFrameApplication().getSelectedInfo().getSpecialty_id()).add("more_live","1").add("is_new",1).add("new_banner",1);
                manager.netWork(NetManger.mService.getBannerLive(Host.EDU_OPENAPI+Method.GETCAROUSELPHOTO,live),pPresenter,whichApi,params[0]);
                break;
        }
    }
}
