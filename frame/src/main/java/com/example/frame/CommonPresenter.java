package com.example.frame;

import java.lang.ref.SoftReference;

public class CommonPresenter <V extends ICommonView,M extends ICommonModel>implements ICommonPresenter {
    private SoftReference<V> iCommonView;
    private SoftReference<M> iCommonModel;

    public CommonPresenter(V iView, M iModel) {
        iCommonView = new SoftReference<>(iView);
        iCommonModel =new SoftReference<>(iModel);
    }

    @Override
    public void onSuccess(int whichApi, int loadType, Object... pD) {
        if (iCommonView!=null&&iCommonView.get()!=null){
            iCommonView.get().onSuccess(whichApi,loadType,pD);
        }

    }

    @Override
    public void onFailed(int whichApi, Throwable pThrowable) {
        if (iCommonView!=null&&iCommonView.get()!=null){
            iCommonView.get().onFailed(whichApi,pThrowable);
        }

    }
    /*
    * 发起普通的网络请求
    * */
    @Override
    public void getData(int whichApi, Object... pPs) {
        if (iCommonModel!=null&&iCommonModel.get()!=null){
            iCommonModel.get().getData(this,whichApi,pPs);
        }

    }
    public void clear(){
        if (iCommonView!=null){
            iCommonView.clear();
            iCommonView=null;
        }
        if (iCommonModel!=null){
            iCommonModel.clear();
            iCommonModel=null;
        }
    }
//    @Override
//    public void getData(int whichApi, int loadType, Object[] pPs) {
//        iCommonModel.getData(this,whichApi,loadType,pPs);
//    }

    /*
    * 发起涉及刷新和加载的网络请求
    * */
//    @Override
//    public void getTrendsData(int whichApi, int loadType, Object... pPs) {
//        iCommonModel.getTrendsDate(this,whichApi,loadType,pPs);
//    }
}
