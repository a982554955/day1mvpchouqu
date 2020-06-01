package com.example.day1mvpchouqu;

import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.data.TestInfo;
import com.example.day1mvpchouqu.adapter.MainAdapter;
import com.example.day1mvpchouqu.base.BaseMvpActivity;
import com.example.day1mvpchouqu.interfaces.DataListener;
import com.example.day1mvpchouqu.model.TestModel;
import com.example.frame.ApiConfig;
import com.example.frame.ICommonModel;
import com.example.frame.LoadTypeConfig;
import com.example.frame.utils.ParamHashMap;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity {
    @BindView(R.id.recyclerView)
    RecyclerView mRec;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private MainAdapter adapter;
    private ArrayList<TestInfo.DataInfo> dataInfos= new ArrayList<>();
    private int pageId = 0;
    private Map<String, Object> paramHashMap;
    String string="111";

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public ICommonModel setModel() {
        return new TestModel();
    }

    @Override
    public void setUpView() {
        paramHashMap = new ParamHashMap().add("c", "api").add("a", "getList");
        initRecyclerView(mRec, smartRefreshLayout, new DataListener() {
            @Override
            public void dataType(int mode) {
                if (mode == LoadTypeConfig.REFRESH) {
                    pageId = 0;
                    commonPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.REFRESH, paramHashMap, pageId);
                } else {
                    pageId++;
                    commonPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.MORE, paramHashMap, pageId);
                }
            }
        });
        adapter = new MainAdapter(this, dataInfos);
        mRec.setAdapter(adapter);
    }

    @Override
    public void setUpData() {
        commonPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.NORMAL, paramHashMap, pageId);
    }

    @Override
    public void netSuccess(int whichApi, int loadType, Object[] pD) {
        switch (whichApi) {
            case ApiConfig.TEST_GET:
                if (loadType == LoadTypeConfig.MORE) {
                    smartRefreshLayout.finishLoadMore();
                } else if (loadType == LoadTypeConfig.REFRESH) {
                    smartRefreshLayout.finishRefresh();
                    dataInfos.clear();
                }
                List<TestInfo.DataInfo> list = ((TestInfo) pD[0]).datas;
                MainActivity.this.dataInfos.addAll(list);
                adapter.notifyDataSetChanged();
                break;
        }
    }


    @Override
    public void onFailed(int whichApi, Throwable pThrowable) {
        Toast.makeText(this, pThrowable.getMessage() != null ? pThrowable.getMessage() : "网络请求发送错误", Toast.LENGTH_SHORT).show();
    }

}
