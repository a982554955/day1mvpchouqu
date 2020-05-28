package com.example.day1mvpchouqu;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.TestInfo;
import com.example.day1mvpchouqu.adapter.MainAdapter;
import com.example.frame.ApiConfig;
import com.example.frame.CommonPresenter;
import com.example.frame.ICommonView;
import com.example.frame.LoadTypeConfig;
import com.example.frame.TestModel;
import com.example.frame.utils.ParamHashMap;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements ICommonView {

    private RecyclerView mRec;
    private CommonPresenter commonPresenter;
    private TestModel testModel;
    private MainAdapter adapter;
    private ArrayList<TestInfo.DataInfo> dataInfos;
    private SmartRefreshLayout smartRefreshLayout;
    private int pageId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testModel = new TestModel();
        commonPresenter = new CommonPresenter(this,testModel);
        initView();
    }

    private void initView() {
        mRec = (RecyclerView) findViewById(R.id.recyclerView);
        smartRefreshLayout = findViewById(R.id.refreshLayout);
       final Map<String,Object> paramHashMap = new ParamHashMap().add("c", "api").add("a", "getList");
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageId=0;
                commonPresenter .getData(ApiConfig.TEST_GET, LoadTypeConfig.REFRESH,paramHashMap,pageId);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageId++;
                commonPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.MORE,paramHashMap,pageId);
            }
        });

        mRec.setLayoutManager(new LinearLayoutManager(this));
        mRec.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        dataInfos = new ArrayList<>();
        adapter = new MainAdapter(this, dataInfos);
        mRec.setAdapter(adapter);

        commonPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.NORMAL,paramHashMap,pageId);
    }



    @Override
    public void onSuccess(int whichApi, int loadType, Object[] pD) {
        switch (whichApi){
            case ApiConfig.TEST_GET:
                if (loadType==LoadTypeConfig.MORE){
                    smartRefreshLayout.finishLoadMore();
                }else if (loadType==LoadTypeConfig.REFRESH){
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
        Toast.makeText(this, pThrowable.getMessage()!=null?pThrowable.getMessage():"网络请求发送错误", Toast.LENGTH_SHORT).show();
    }
}
