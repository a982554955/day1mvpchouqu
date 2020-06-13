package com.example.day1mvpchouqu.adapter;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.VIPBannerBean;
import com.example.data.VIPListBean;
import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.view.design.BannerLayout;
import com.example.zhulongstudygroup.adapter.VIPLiveAdapter;

import java.util.ArrayList;

public class VIPFragmentAdapter extends RecyclerView.Adapter<VIPFragmentAdapter.ViewHolder> {
    private ArrayList<String> bannerData;
    private ArrayList<VIPBannerBean.ResultBean.LiveBeanX.LiveBean> liveData;
    private ArrayList<VIPListBean.ResultBean.ListBean> recycleData;
    private Activity mContext;

    private final int BANNER = 1, LIVE = 2, LIST = 3;
    private VipListAdapter vipListAdapter;


    public VIPFragmentAdapter(ArrayList<String> pBannerData, ArrayList<VIPBannerBean.ResultBean.LiveBeanX.LiveBean> pLiveData, ArrayList<VIPListBean.ResultBean.ListBean> pRecycleData, Activity pContext) {
        bannerData = pBannerData;
        liveData = pLiveData;
        recycleData = pRecycleData;
        mContext = pContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @LayoutRes int layoutId;
        if (viewType == BANNER) {
            layoutId = R.layout.item_homepage_ad;
        } else if (viewType == LIVE) {
            layoutId = R.layout.live_recycler_item;
        } else {
            layoutId = R.layout.item_vip_list;
        }

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false), viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER){
            holder.banner.attachActivity(mContext);
            if (bannerData.size()!=0)holder.banner.setViewUrls(bannerData);
        } else if (getItemViewType(position) == LIVE) {
            LinearLayoutManager manager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
            holder.recyclerView.setLayoutManager(manager);
           VIPLiveAdapter adapter = new VIPLiveAdapter(liveData, mContext);
            holder.recyclerView.setAdapter(adapter);
        }else {
            VipListAdapter vipListAdapter = new VipListAdapter(recycleData, mContext);
            holder.rv.setLayoutManager(new GridLayoutManager(mContext,2));
            holder.rv.setAdapter(vipListAdapter);

        }
    }

    @Override
    public int getItemViewType(int position) {
        int type = LIST;
        if (position == 0) type = BANNER;
        else if (liveData != null && liveData.size() != 0 && position == 1) type = LIVE;
        else type = LIST;

        return type;
    }

    @Override
    public int getItemCount() {
        return liveData != null && liveData.size() != 0 ? recycleData.size() + 2 : recycleData.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private BannerLayout banner;
        private RecyclerView recyclerView;
        private RecyclerView rv;

        public ViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            if (viewType == BANNER) banner = itemView.findViewById(R.id.banner_main);
            if (viewType == LIVE) recyclerView = itemView.findViewById(R.id.recyclerView);
            if (viewType == LIST){
                rv= itemView.findViewById(R.id.rv);
            }
        }
    }
}
