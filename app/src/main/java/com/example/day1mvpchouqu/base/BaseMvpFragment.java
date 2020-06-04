package com.example.day1mvpchouqu.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import frame.CommonPresenter;
import frame.ICommonModel;
import frame.ICommonView;

public abstract class BaseMvpFragment<M extends ICommonModel>  extends BaseFragment implements ICommonView {
    private M mModel;
    public CommonPresenter mPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), null);
        ButterKnife.bind(this,view);
        mModel = setModel();
        mPresenter = new CommonPresenter(this, mModel);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpView();
        setUpData();
    }

    protected abstract int getLayout();
    public abstract M setModel();
    public abstract void setUpView();

    public abstract void setUpData();

    @Override
    public void onSuccess(int whichApi, Object[] pD) {

    }

    @Override
    public void onFailed(int whichApi, Throwable pThrowable) {

    }
}
