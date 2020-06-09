package com.example.day1mvpchouqu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.SearchItemEntity;
import com.example.day1mvpchouqu.R;
import com.yiyatech.utils.newAdd.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseChildAdapter extends RecyclerView.Adapter<CourseChildAdapter.ViewHolder> {

    private List<SearchItemEntity> mCourseListInfos;
    private Context mContext;

    public CourseChildAdapter(List<SearchItemEntity> pCourseListInfos, Context pContext) {
        mCourseListInfos = pCourseListInfos;
        mContext = pContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.course_adapter_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mCourseListInfos == null || mCourseListInfos.size() == 0) return;
        SearchItemEntity entity = mCourseListInfos.get(position);
        holder.tvTitle.setText(entity.getLesson_name());
        GlideUtil.loadImage(holder.ivPhoto, entity.getThumb());
        holder.tvLearnNum.setText(entity.getStudentnum() + "人学习");
        holder.tvLikeNum.setText("好评率" + entity.getRate());
        holder.tvPrice.setText("￥" + entity.getPrice());
    }

    @Override
    public int getItemCount() {
        return mCourseListInfos != null ? mCourseListInfos.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_photo)
        ImageView ivPhoto;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_learn_num)
        TextView tvLearnNum;
        @BindView(R.id.tv_like_num)
        TextView tvLikeNum;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
