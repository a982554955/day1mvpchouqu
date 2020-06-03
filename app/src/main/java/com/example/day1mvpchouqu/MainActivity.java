package com.example.day1mvpchouqu;

import androidx.recyclerview.widget.RecyclerView;

import com.example.data.TestInfo;
import com.example.day1mvpchouqu.adapter.TestAdapter;
import com.example.day1mvpchouqu.base.BaseMvpActivity;
import com.example.day1mvpchouqu.model.TestModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import frame.ApiConfig;
import frame.ICommonModel;
import frame.LoadTypeConfig;
import frame.utils.ParamHashMap;

public class MainActivity extends BaseMvpActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private TestAdapter mTestAdapter;
    private int pageId = 0;
    private List<TestInfo.DataInfo> datas = new ArrayList<>();
    private Map<String, Object> mParams;


    @Override
    public ICommonModel setModel() {
        return new TestModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setUpView() {
        mParams = new ParamHashMap().add("c", "api").add("a", "getList");
        initRecyclerView(mRecyclerView, mRefreshLayout, mode -> {
            if (mode == LoadTypeConfig.REFRESH){
                pageId = 0;
                mPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.REFRESH, mParams, pageId);
            } else {
                pageId++;
                mPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.MORE, mParams, pageId);
            }
        });

        mTestAdapter = new TestAdapter(datas, this);
        mRecyclerView.setAdapter(mTestAdapter);
    }

    @Override
    public void setUpData() {
        mPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.NORMAL, mParams, pageId);
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi) {
            case ApiConfig.TEST_GET:
                if ((int)pD[0] == LoadTypeConfig.MORE) {
                    mRefreshLayout.finishLoadMore();
                } else if ((int)pD[0] == LoadTypeConfig.REFRESH) {
                    mRefreshLayout.finishRefresh();
                    datas.clear();
                }
                List<TestInfo.DataInfo> datas = ((TestInfo) pD[0]).datas;
                MainActivity.this.datas.addAll(datas);
                mTestAdapter.notifyDataSetChanged();
                break;
        }
    }
}
