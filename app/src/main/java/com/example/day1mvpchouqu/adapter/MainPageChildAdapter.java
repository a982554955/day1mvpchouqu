package com.example.day1mvpchouqu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.data.BannerLiveInfo;
import com.example.day1mvpchouqu.R;

import java.util.List;

import butterknife.BindView;

public class MainPageChildAdapter extends RecyclerView.Adapter<MainPageChildAdapter.ViewHolder> {

    private Context mContext;
    private List<BannerLiveInfo.Live> mLivesData;

    public MainPageChildAdapter(Context pContext, List<BannerLiveInfo.Live> pLivesData) {
        mContext = pContext;
        mLivesData = pLivesData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.live_child_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BannerLiveInfo.Live live = mLivesData.get(position);
        holder.tvActivityName.setText(live.activityName);
        holder.tvStartDate.setText(live.endTime);
        Glide.with(mContext).load(live.teacher_photo).apply(new RequestOptions().circleCrop()).into(holder.civTeacherPhoto);
    }

    @Override
    public int getItemCount() {
        return mLivesData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView civTeacherPhoto;
        TextView tvActivityName;
        ImageView imgAlarm;
        TextView tvStartDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            civTeacherPhoto= itemView.findViewById(R.id.civ_teacher_photo);
            tvActivityName= itemView.findViewById(R.id.tv_activityName);
            imgAlarm= itemView.findViewById(R.id.img_alarm);
            tvStartDate= itemView.findViewById(R.id.tv_start_date);
        }
    }
}
