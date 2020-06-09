package com.example.day1mvpchouqu.model;

import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.constants.Method;

import frame.ApiConfig;
import frame.FrameApplication;
import frame.Host;
import frame.ICommonModel;
import frame.ICommonPresenter;
import frame.NetManger;
import frame.utils.ParamHashMap;

public class DataModel implements ICommonModel {
    @Override
    public void getData(ICommonPresenter pPresenter, int whichApi, Object[] params) {
        switch (whichApi){
            case ApiConfig.DATA_GROUP:
                ParamHashMap add = new ParamHashMap().add("type", 1).add("fid", FrameApplication.getFrameApplication().getSelectedInfo().getFid()).add("page", params[1]);
                NetManger.getInstance().netWork(NetManger.mService.getGroupList(Host.BBS_OPENAPI+ Method.GETGROUPLIST,add),pPresenter,whichApi,params[0]);
                break;
            case ApiConfig. CLICK_CANCEL_FOCUS:
                ParamHashMap add1 = new ParamHashMap().add("group_id", params[0]).add("type", 1).add("screctKey", FrameApplication.getFrameApplicationContext().getString(R.string.secrectKey_posting));
                NetManger.getInstance().netWork(NetManger.mService.removeFocus(Host.BBS_API+Method.REMOVEGROUP,add1),pPresenter,whichApi,params[1]);
                break;
                case ApiConfig.CLICK_TC_FOCUS:
                    ParamHashMap add2 = new ParamHashMap().add("gid", params[0]).add("group_name", params[1]).add("joingroup", FrameApplication.getFrameApplicationContext().getString(R.string.secrectKey_posting));
                    NetManger.getInstance().netWork(NetManger.mService.joinFocus(Host.BBS_API+Method.JOINGROUP,add2),pPresenter,whichApi,params[2]);
                    break;
        }
    }
}
