package com.example.frame;

public interface ICommonModel<T> {
    /*
    *
    * 用于model层执行耗时任务 ，不处理刷新和加载逻辑
    * whichApi 区别接口的标识
    * params 泛型可变参数
    * */
    void getData(ICommonPresenter iCommonPresenter,int whichApi,T...params);
    /*
     *
     * 用于model层执行耗时任务 ，不处理刷新和加载逻辑
     * whichApi 区别接口的标识
     * params 泛型可变参数
     * */
//    void getData(ICommonPresenter iCommonPresenter,int whichApi,int dataType,T...params);
    /**
     * 用于model层执行耗时任务,处理刷新和加载逻辑
     * 如果等到8.0以下手机淘汰的那一天，我们可以将以下方法替代上面的方法，这样可以避免烦躁的强制重写
     * whichApi 区别接口的标识
     * datatype 区分刷新，正常请求 ，加载更多的标识
     * praams 泛型可变参数
     * */
//    default void getTrendsDate(ICommonPresenter iCommonPresenter,int whichApi,int dataType,T...params){}
}
