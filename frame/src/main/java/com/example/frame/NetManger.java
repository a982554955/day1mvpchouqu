package com.example.frame;


import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 任小龙 on 2019/12/19.
 */
public class NetManger {
    private NetManger() {
    }

    private static volatile NetManger sNetManger;

    public static NetManger getInstance() {
        if (sNetManger == null) {
            synchronized (NetManger.class) {
                sNetManger = new NetManger();
            }
        }
        return sNetManger;
    }
    /**
     * t 如果传baseurl,用传过来，没传的话用默认
     * */
    public <T> IsApiser getService(T... t) {
        String baseUrl = ServerAddressConfig.BASE_URL;
        if (t != null && t.length != 0) {
            baseUrl = (String) t[0];
        }
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(initClient())

                .build()
                .create(IsApiser.class);
    }

    private OkHttpClient initClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(new CommonHeadersInterceptor());
        builder.addInterceptor(new CommonParamsInterceptor());
        builder.addInterceptor(initLogInterceptor());
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(15,TimeUnit.SECONDS);
        return builder.build();
    }

    private Interceptor initLogInterceptor() {
        HttpLoggingInterceptor log = new HttpLoggingInterceptor();
        log.setLevel(HttpLoggingInterceptor.Level.BODY);
        return log;
    }

    /**
     * 使用observce观察者  抽取出网络请求及切换线程的过程
     *
     * */
    public <T, O> void netWork(Observable<T> localTestInfo, final ICommonPresenter pPresenter, final int whichApi, final int dataType, O... o) {
        localTestInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        pPresenter.addObservce(d);
                    }

                    @Override
                    public void onSuccess(Object value) {
                        pPresenter.onSuccess(whichApi,dataType,value,o);
                    }

                    @Override
                    public void onFailed(Throwable e) {
                    pPresenter.onFailed(whichApi,e);
                    }
                });
    }
    /**
     * 使用consumer观察者  抽取出网络请求及切换线程的过程
     *
     * */
    public <T, O> void netWorkByconsumer(Observable<T> localTestInfo, final ICommonPresenter pPresenter, final int whichApi, final int dataType, O... o) {
        Disposable subscribe = localTestInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(t -> pPresenter.onSuccess(whichApi,dataType,t,o),throwable -> pPresenter.onFailed(whichApi,throwable));
        pPresenter.addObservce(subscribe);
    }
}
