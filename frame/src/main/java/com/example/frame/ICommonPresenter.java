package com.example.frame;

import io.reactivex.disposables.Disposable;

public interface ICommonPresenter<P> extends ICommonView{
    //1.由他作为中间层来发起网络请求；2.将请求得结果回调到View层
    void getData(int whichApi,P...pPs);
    //void getData(int whichApi,int loadType,P...pPs);
//    default void getTrendsData(int whichApi,int loadType,P...pPs){}
     void addObservce(Disposable d);
}
