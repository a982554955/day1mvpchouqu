package com.example.frame;

import com.example.data.TestInfo;
import com.example.frame.utils.ParamHashMap;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestModel implements ICommonModel {


    @Override
    public void getData(ICommonPresenter iCommonPresenter, int whichApi, Object[] params) {
            int loadType= (int) params[0];
            Map map= (Map) params[1];
            int pageId= (int) params[2];
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://static.owspace.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Observable<TestInfo> observable = retrofit.create(IsApiser.class).getTestData(map, pageId);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TestInfo>() {
                    @Override
                    public void accept(TestInfo testInfo) throws Exception {
                        iCommonPresenter.onSuccess(whichApi, loadType,testInfo);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        iCommonPresenter.onFailed(whichApi,throwable);
                    }
                });
     /*   observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        testInfo -> iCommonPresenter.
                                onSuccess(whichApi,dataType,testInfo),
                        throwable -> iCommonPresenter.
                                onFailed(whichApi,throwable));*/
    }

//    @Override
//    public void getData(ICommonPresenter iCommonPresenter, int whichApi, int dataType, Object[] params) {
//
     /*   observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        testInfo -> iCommonPresenter.
                                onSuccess(whichApi,dataType,testInfo),
                        throwable -> iCommonPresenter.
                                onFailed(whichApi,throwable));*/
    }

//    @Override
//    public void getTrendsDate(ICommonPresenter iCommonPresenter, int whichApi, int dataType, Object[] params) {
//
//    }

