package com.example.day1mvpchouqu.view.fragment;

import androidx.recyclerview.widget.RecyclerView;

import com.example.data.BaseInfo;
import com.example.data.EssencePostEntity;
import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.adapter.DataEssenceAdapter;
import com.example.day1mvpchouqu.base.BaseMvpFragment;
import com.example.day1mvpchouqu.interfaces.DataListener;
import com.example.day1mvpchouqu.model.DataModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import frame.ApiConfig;
import frame.LoadTypeConfig;

public class RecentilBestFragment extends BaseMvpFragment<DataModel> implements DataListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    int page = 1;
    private List<EssencePostEntity> mList = new ArrayList<>();
    private DataEssenceAdapter mAdapter;


    public static RecentilBestFragment newInstance() {
        RecentilBestFragment fragment = new RecentilBestFragment();
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.refresh_list_layout;
    }

    @Override
    public DataModel setModel() {
        return new DataModel();
    }

    @Override
    public void setUpView() {

        initRecyclerView(recyclerView, refreshLayout, this);
        mAdapter = new DataEssenceAdapter(getContext(), mList);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setUpData() {
        mPresenter.getData(ApiConfig.DATA_ESSENCE, LoadTypeConfig.NORMAL, page);
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi) {
            case ApiConfig.DATA_ESSENCE:
                BaseInfo<List<EssencePostEntity>> info = (BaseInfo<List<EssencePostEntity>>) pD[0];
                if (info.isSuccess()) {
                    List<EssencePostEntity> result = info.result;
                    int loadMode = (int) pD[1];
                    if (loadMode == LoadTypeConfig.REFRESH) {
                        mList.clear();
                        refreshLayout.finishRefresh();
                    } else if (loadMode == LoadTypeConfig.MORE) {
                        refreshLayout.finishLoadMore();
                    }
                    mList.addAll(result);
                    mAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void dataType(int mode) {
        if (mode == LoadTypeConfig.REFRESH) {
            mPresenter.getData(ApiConfig.DATA_ESSENCE, LoadTypeConfig.REFRESH, 1);
        } else if (mode == LoadTypeConfig.MORE) {
            page++;
            mPresenter.getData(ApiConfig.DATA_ESSENCE, LoadTypeConfig.MORE, page);
        }
    }
}
