package com.example.day1mvpchouqu.model;

import android.content.Context;

import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.base.Application1907;
import com.example.frame.ApiConfig;
import com.example.frame.FrameApplication;
import com.example.frame.ICommonModel;
import com.example.frame.ICommonPresenter;
import com.example.frame.NetManger;
import com.example.frame.utils.ParamHashMap;


/**
 * Created by 任小龙 on 2020/6/3.
 */
public class AccountModel implements ICommonModel {
    private NetManger mManger = NetManger.getInstance();
    private Context mContext = Application1907.get07ApplicationContext();

    @Override
    public void getData(ICommonPresenter pPresenter, int whichApi, Object[] params) {
        switch (whichApi) {
            case ApiConfig.SEND_VERIFY:
                mManger.netWork(mManger.getService(mContext.getString(R.string.passport_openapi_user)).getLoginVerify((String) params[0]), pPresenter, whichApi);
                break;
            case ApiConfig.VERIFY_LOGIN:
                mManger.netWork(mManger.getService(mContext.getString(R.string.passport_openapi_user)).loginByVerify(new ParamHashMap().add("mobile",params[0]).add("code",params[1])),pPresenter,whichApi);
                break;
            case ApiConfig.GET_HEADER_INFO:
                String uid = FrameApplication.getFrameApplication().getLoginInfo().getUid();
                mManger.netWork(mManger.getService(mContext.getString(R.string.passport_api)).getHeaderInfo(new ParamHashMap().add("zuid",uid).add("uid",uid)),pPresenter,whichApi);
                break;
        }
    }
}
