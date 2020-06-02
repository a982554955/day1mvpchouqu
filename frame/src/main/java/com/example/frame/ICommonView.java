package com.example.frame;

public interface ICommonView<D> {
    /**
     * 成功回调
     * whichApi 成功接口的标识（哪个接口成了）
     * loadType 类型的回调（正常加载，刷新，加载更多）
     * pD 一般是实体类的回调，但为了框架的灵活性，确保其他一些数据的偶发性问题，故未将长度写死
     * */
    void onSuccess(int whichApi,D...pD);
    /**
     * 失败的回调
     * whichApi 是哪个接口失败了
     * pThrowable 失败的具体描述
     * */
    void onFailed(int whichApi,Throwable pThrowable);
}
