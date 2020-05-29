package com.example.day1mvpchouqu.base;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.day1mvpchouqu.interfaces.DataListener;
import com.example.frame.LoadTypeConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void initRecyclerView(RecyclerView pRecyclerView, final SmartRefreshLayout pSmartRefreshLayout, final DataListener dataListener){
        if (pRecyclerView!=null){
            pRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        if (pSmartRefreshLayout!=null){
            pSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    if (pSmartRefreshLayout!=null){
                        dataListener.dataType(LoadTypeConfig.REFRESH);
                    }

                }

                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    if (dataListener!=null){
                        dataListener.dataType(LoadTypeConfig.MORE);
                    }

                }
            });
        }
    }

    public void showLog(Object content){
        Log.e("睚眦", content.toString() );
    }

    public void showToast(Object content){
        Toast.makeText(getApplicationContext(), content.toString(), Toast.LENGTH_SHORT).show();
    }
}
