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


public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    /**
     * 在recyclerview 在整个项目中使用比较频繁，将公共代码j进行抽取
     * pRecyclerView   要操作的Recyclerview
     *  pSmartRefreshLayout 如果有刷新和加载更多的问题，所使用的SmartRefreshLayout
     *  dataListener 刷新和加载的监听，如果实际的使用中不涉及到刷新和加载更多，直接传null
     * */
    public void initRecyclerView(RecyclerView pRecyclerView, final SmartRefreshLayout pSmartRefreshLayout, final DataListener dataListener){
        if (pRecyclerView!=null)pRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (pSmartRefreshLayout!=null&&dataListener!=null)
       {
           pSmartRefreshLayout.setOnLoadMoreListener(refreshLayout -> dataListener.dataType(LoadTypeConfig.REFRESH));
           pSmartRefreshLayout.setOnRefreshListener(refreshLayout -> dataListener.dataType(LoadTypeConfig.MORE));
        }
    }

    public void showLog(Object content){
        Log.e("睚眦", content.toString() );
    }

    public void showToast(Object content){
        Toast.makeText(getApplicationContext(), content.toString(), Toast.LENGTH_SHORT).show();
    }
}
