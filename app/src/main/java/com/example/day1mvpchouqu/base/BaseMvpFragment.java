package com.example.day1mvpchouqu.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import frame.CommonPresenter;
import frame.ICommonModel;
import frame.ICommonView;

public abstract class BaseMvpFragment<M extends ICommonModel>  extends BaseFragment implements ICommonView {
    private M mModel;
    public CommonPresenter mPresenter;
    private Unbinder mBind;
    private boolean isInit;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), null);
        mBind= ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpView();
        if (mModel==null)mModel = setModel();
       if (mPresenter==null)mPresenter = new CommonPresenter(this, mModel);
        if (!isInit){
            setUpData();
            isInit=true;
        }

    }

    protected abstract int getLayout();
    public abstract M setModel();
    public abstract void setUpView();

    public abstract void setUpData();
    public abstract void netSuccess(int whichApi, Object[] pD);
    public void netFailed(int whichApi, Throwable pThrowable){}
    @Override
    public void onSuccess(int whichApi, Object[] pD) {
        netSuccess(whichApi,pD);
    }

    @Override
    public void onFailed(int whichApi, Throwable pThrowable) {
        showLog("net work error: "+whichApi+"error content"+ pThrowable != null && !TextUtils.isEmpty(pThrowable.getMessage()) ? pThrowable.getMessage() : "不明错误类型");
        netFailed(whichApi,pThrowable);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.clear();
        if (mBind != null)mBind.unbind();
    }
}
