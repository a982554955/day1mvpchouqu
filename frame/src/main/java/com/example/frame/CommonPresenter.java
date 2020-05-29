package com.example.frame;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class CommonPresenter <V extends ICommonView,M extends ICommonModel>implements ICommonPresenter {
    private SoftReference<V> iCommonView;
    private SoftReference<M> iCommonModel;
    private List<Disposable>disposablesList;
    /**
     * 构造中，接受view和model对象
     * */
    public CommonPresenter(V iView, M iModel) {
        disposablesList=new ArrayList<>();
        iCommonView = new SoftReference<>(iView); //软引用包裹 当内存不足的时候确保能够回收，避免内存溢出
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
    * whichApi 接口标识
    * pPs 作为公共封装的方法，并不知道未来传递的是什么类型的参数，也不知道会传递多少个，所以通过泛型可变参数来声明形参
    * */
    @Override
    public void getData(int whichApi, Object... pPs) {
        if (iCommonModel!=null&&iCommonModel.get()!=null){
            iCommonModel.get().getData(this,whichApi,pPs);
        }

    }
    /**
     * 将所有网络请求的订阅关系，统一到中间层的集合中，用于view销毁时统一处理
     * d 订阅对象
     * */
    @Override
    public void addObservce(Disposable d) {
        if (disposablesList==null)return;
        disposablesList.add(d);
    }

    public void clear(){
        /**当activity页面销毁执行OnDestroy时，这个时候已经没有展示数据的需求了，所有首先要将在该页面进行的所有网络请求
        终止，以免GC回收时发现view仍被持有引用导致不能回收，导致内存泄漏，当然这个即使不处理，这个泄露的时间会很短暂，当Gc线程
       下一次检查到该对象，网络任务如果已经完成，并不影响activity的回收

         在MVP使用中,为了实现视图和数据的解耦，我们通过中间层presenter来进行双向绑定和处理，但是当view销毁时，由于P层仍然
         持有view的引用，也可能会发生View不能被回收导致内容泄露的情况，所以当页面销毁时，将presenter同view和model进行解绑
        */
        for(Disposable dis:disposablesList){
            if (dis!=null&&!dis.isDisposed()){
                dis.dispose();
            }
        }
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
