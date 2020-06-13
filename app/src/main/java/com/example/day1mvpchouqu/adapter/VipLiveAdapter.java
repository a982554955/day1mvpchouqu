package com.example.zhulongstudygroup.adapter;


import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.VIPBannerBean;
import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.view.design.RoundImage;
import com.yiyatech.utils.newAdd.GlideUtil;
import com.yiyatech.utils.newAdd.TimeUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VIPLiveAdapter extends RecyclerView.Adapter<VIPLiveAdapter.ViewHolder> {

    private ArrayList<VIPBannerBean.ResultBean.LiveBeanX.LiveBean> liveData;
    private Context mContext;

    public VIPLiveAdapter(ArrayList<VIPBannerBean.ResultBean.LiveBeanX.LiveBean> pLiveData, Context pContext) {
        liveData = pLiveData;
        mContext = pContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.live_adapter_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VIPBannerBean.ResultBean.LiveBeanX.LiveBean live = liveData.get(position);
        GlideUtil.loadImage(holder.roundPhoto,live.getTeacher_photo());
        holder.title.setText(live.getActivityName());
        if (!TextUtils.isEmpty(live.getStartTime()))holder.time.setText(TimeUtil.formatLiveTime(Long.parseLong(live.getStartTime())));
    }

    @Override
    public int getItemCount() {
        return liveData != null ? liveData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.round_photo)
        RoundImage roundPhoto;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.time)
        TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
