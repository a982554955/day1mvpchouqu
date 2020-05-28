package com.example.frame;

public class CommonPresenter implements ICommonPresenter {
    private ICommonView iCommonView;
    private ICommonModel iCommonModel;

    public CommonPresenter(ICommonView iCommonView, ICommonModel iCommonModel) {
        this.iCommonView = iCommonView;
        this.iCommonModel = iCommonModel;
    }





    @Override
    public void onSuccess(int whichApi, int loadType, Object... pD) {
     iCommonView.onSuccess(whichApi,loadType,pD);
    }

    @Override
    public void onFailed(int whichApi, Throwable pThrowable) {
    iCommonView.onFailed(whichApi,pThrowable);
    }
    /*
    * 发起普通的网络请求
    * */
    @Override
    public void getData(int whichApi, Object... pPs) {
        iCommonModel.getData(this,whichApi,pPs);
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
