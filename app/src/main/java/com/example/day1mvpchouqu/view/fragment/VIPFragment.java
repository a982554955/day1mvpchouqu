package com.example.day1mvpchouqu.view.fragment;


import androidx.recyclerview.widget.RecyclerView;

import com.example.data.VIPBannerBean;
import com.example.data.VIPListBean;
import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.adapter.VIPFragmentAdapter;
import com.example.day1mvpchouqu.base.BaseMvpFragment;
import com.example.day1mvpchouqu.interfaces.DataListener;
import com.example.zhulongstudygroup.model.VIPModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;


import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import frame.ApiConfig;
import frame.ICommonModel;
import frame.LoadTypeConfig;

public class VIPFragment extends BaseMvpFragment<VIPModel> implements DataListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private int vipPage = 1;

    private ArrayList<String> bannerData = new ArrayList<>();
    private ArrayList<VIPBannerBean.ResultBean.LiveBeanX.LiveBean> liveData = new ArrayList<>();
    private ArrayList<VIPListBean.ResultBean.ListBean> recycleData = new ArrayList<>();
    private VIPFragmentAdapter mAdapter;

    @Override
    protected int getLayout() {
        return R.layout.refresh_list_layout;
    }

    @Override
    public VIPModel setModel() {
        return new VIPModel();
    }


    @Override
    public void setUpView() {
        initRecyclerView(recyclerView, refreshLayout, this);

        mAdapter = new VIPFragmentAdapter(bannerData, liveData, recycleData, getActivity());
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void setUpData() {
        mPresenter.getData(ApiConfig.VIP_BANNER, LoadTypeConfig.NORMAL);
        mPresenter.getData(ApiConfig.VIP_LIST, LoadTypeConfig.NORMAL, vipPage);
    }

    private boolean mainList = false, banLive = false;

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi) {
            case ApiConfig.VIP_BANNER:
                VIPBannerBean vipBannerBean = (VIPBannerBean) pD[0];
                if (vipBannerBean != null && vipBannerBean.getErrNo() == 0) {
                    int loadType = (int)pD[1];
                    if (loadType == LoadTypeConfig.REFRESH) {
                        bannerData.clear();
                        liveData.clear();
                    }
                    List<VIPBannerBean.ResultBean.LunbotuBean> lunbotu = vipBannerBean.getResult().getLunbotu();
                    for (int i = 0; i < lunbotu.size(); i++) {
                        bannerData.add(lunbotu.get(i).getImg());
                    }

                    List<VIPBannerBean.ResultBean.LiveBeanX.LiveBean> live = vipBannerBean.getResult().getLive().getLive();
                    if (live != null && live.size() != 0) {
                        liveData.addAll(live);
                    }
                    banLive = true;
                    if (mainList) {
                        mAdapter.notifyDataSetChanged();
                        banLive = false;
                    }
                }
                break;
            case ApiConfig.VIP_LIST:
                int loadMode = (int)pD[1];
                VIPListBean vipListBean = (VIPListBean) pD[0];
                if (loadMode == LoadTypeConfig.MORE) refreshLayout.finishLoadMore();
                if (loadMode == LoadTypeConfig.REFRESH) {
                    recycleData.clear();
                    refreshLayout.finishRefresh();
                }
                recycleData.addAll(vipListBean.getResult().getList());
                mainList = true;
                if (banLive) {
                    mAdapter.notifyDataSetChanged();
                    mainList = false;
                }

                break;
        }
    }

        @Override
        public void dataType ( int mode){
            if (mode == LoadTypeConfig.REFRESH) {
                liveData.clear();
                recycleData.clear();
                mPresenter.getData(ApiConfig.VIP_BANNER, LoadTypeConfig.REFRESH);
                mPresenter.getData(ApiConfig.VIP_LIST, LoadTypeConfig.REFRESH, vipPage);
            } else if (mode==LoadTypeConfig.MORE){
                vipPage++;
                mPresenter.getData(ApiConfig.VIP_LIST, LoadTypeConfig.MORE, vipPage);
            }
        }
    }
