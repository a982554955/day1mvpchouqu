package com.example.day1mvpchouqu.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.example.day1mvpchouqu.interfaces.DataListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.ButterKnife;
import frame.ICommonModel;
import frame.ICommonView;
import frame.LoadTypeConfig;

public  class BaseFragment extends Fragment {

    public void initRecyclerView(RecyclerView pRecyclerView, SmartRefreshLayout pRefreshLayout, DataListener pDataListener) {
        if (pRecyclerView != null) {
            pRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            //禁掉动画
            ((SimpleItemAnimator)pRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        }
        if (pRefreshLayout != null && pDataListener != null) {
            pRefreshLayout.setOnRefreshListener(refreshLayout -> pDataListener.dataType(LoadTypeConfig.REFRESH));
            pRefreshLayout.setOnLoadMoreListener(refreshLayout -> pDataListener.dataType(LoadTypeConfig.MORE));
        }
    }
    public void showLog(Object content) {
        Log.e("睚眦", content.toString());
    }

    public void showToast(Object content) {
        Toast.makeText(getContext(), content.toString(), Toast.LENGTH_SHORT).show();
    }
    public int setColor(@ColorRes int pColor){
        return ContextCompat.getColor(getContext(),pColor);
    }
}
