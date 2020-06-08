package com.example.day1mvpchouqu.view.fragment;

import android.content.Context;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.data.BannerLiveInfo;
import com.example.data.BaseInfo;
import com.example.data.IndexCommondEntity;
import com.example.data.MainPageInfo;
import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.adapter.MainPageAdapter;
import com.example.day1mvpchouqu.base.BaseMvpFragment;
import com.example.day1mvpchouqu.interfaces.DataListener;
import com.example.day1mvpchouqu.model.MainPageModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import frame.ApiConfig;
import frame.ICommonModel;
import frame.LoadTypeConfig;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainPageFragment extends BaseMvpFragment<MainPageModel> implements DataListener {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private int currentPage = 1;
    private List<IndexCommondEntity> bottomList = new ArrayList<>();
    private List<String>bannerData=new ArrayList<>();
    private List<BannerLiveInfo.Live> mLivesData=new ArrayList<>();
    private MainPageAdapter mMainPageAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_main_page;
    }

    @Override
    public MainPageModel setModel() {
        return new MainPageModel();
    }

    @Override
    public void setUpView() {
        initRecyclerView(recyclerView, refreshLayout, this);
        mMainPageAdapter = new MainPageAdapter(bottomList,bannerData,mLivesData,getContext());
        recyclerView.setAdapter(mMainPageAdapter);
    }

    @Override
    public void setUpData() {
        mPresenter.getData(ApiConfig.MAIN_PAGE_LIST, LoadTypeConfig.NORMAL, currentPage);
        mPresenter.getData(ApiConfig.BANNER_LIVE_DATA,LoadTypeConfig.NORMAL);
    }
        private boolean mainList=false,banLive=false;
    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi) {
            case ApiConfig.MAIN_PAGE_LIST:
                int loadMode = (int) ((Object[]) pD[1])[0];
                BaseInfo<List<IndexCommondEntity>> baseInfo = (BaseInfo<List<IndexCommondEntity>>) pD[0];
                if (baseInfo.isSuccess()){
                    if (loadMode == LoadTypeConfig.MORE) refreshLayout.finishLoadMore();
                    if (loadMode == LoadTypeConfig.REFRESH) {
                        bottomList.clear();
                        refreshLayout.finishRefresh();
                    }
                    bottomList.addAll(baseInfo.result);
                    mainList = true;
                    if (banLive) {
                        mMainPageAdapter.notifyDataSetChanged();
                        mainList = false;
                    }

                }else  showToast("列表加载错误");
                break;
                case ApiConfig.BANNER_LIVE_DATA:
                    JsonObject jsonObject= (JsonObject) pD[0];
                    try {
                        JSONObject object = new JSONObject(jsonObject.toString());
                        if (object.getString("errNo").equals("0")){
                            int load = (int) ((Object[]) pD[1])[0];
                            if (load==LoadTypeConfig.REFRESH){

                            }
                            String result = object.getString("result");
                            JSONObject resultObject = new JSONObject(result);
                            String live = resultObject.getString("live");
                            //如果该字段是以boolean类型返回的，有两种处理方式 1：写两个实体类，一个live类型是Boolean，一个是list 2：当这个字段为Boolean类型时，将其干掉
                            if (live.equals("true")||live.equals("false")){
                                resultObject.remove("live");
                            }
                            result=resultObject.toString();
                            Gson gson=new Gson();
                            BannerLiveInfo info = gson.fromJson(result, BannerLiveInfo.class);
                            for (BannerLiveInfo.Carousel data:info.Carousel) {
                                bannerData.add(data.thumb);
                            }
                            mLivesData.addAll(info.live);
                            banLive = true;
                            if (mainList) {
                                mMainPageAdapter.notifyDataSetChanged();
                                banLive = false;
                            }

                        }
                    } catch (JSONException pE) {
                        pE.printStackTrace();
                    }
                    break;
        }
    }

    @Override
    public void dataType(int mode) {
        if (mode==LoadTypeConfig.REFRESH){
            mPresenter.getData(ApiConfig.MAIN_PAGE_LIST, LoadTypeConfig.NORMAL, currentPage);
            mPresenter.getData(ApiConfig.BANNER_LIVE_DATA,LoadTypeConfig.NORMAL);
        }else {
            mPresenter.getData(ApiConfig.MAIN_PAGE_LIST, LoadTypeConfig.MORE, currentPage);
        }
    }
}
