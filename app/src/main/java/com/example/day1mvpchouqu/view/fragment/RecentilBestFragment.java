package com.example.day1mvpchouqu.view.fragment;

import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.base.BaseMvpFragment;
import com.example.day1mvpchouqu.model.DataModel;

public class RecentilBestFragment extends BaseMvpFragment<DataModel> {
    public static RecentilBestFragment newInstance(){
        RecentilBestFragment fragment=new RecentilBestFragment();
            return fragment;
    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_recently_best;
    }

    @Override
    public DataModel setModel() {
        return new DataModel();
    }

    @Override
    public void setUpView() {

    }

    @Override
    public void setUpData() {

    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {

    }
}
