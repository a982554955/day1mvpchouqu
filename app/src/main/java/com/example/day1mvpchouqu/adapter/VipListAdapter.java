package com.example.day1mvpchouqu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.VIPListBean;
import com.example.day1mvpchouqu.R;
import com.yiyatech.utils.newAdd.GlideUtil;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VipListAdapter extends RecyclerView.Adapter<VipListAdapter.ViewHolder> {
    private ArrayList<VIPListBean.ResultBean.ListBean> list;

    public VipListAdapter(ArrayList<VIPListBean.ResultBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.vip_list, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvCommentRate.setText(list.get(position).getComment_rate()+"好评");
        holder.tvStudentNum.setText(list.get(position).getStudentnum()+"人学习");
        holder.tvTitle.setText(list.get(position).getLesson_name());
        GlideUtil.loadCornerImage(holder.ivThumb,list.get(position).getThumb(),null,10f);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_thumb)
        ImageView ivThumb;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_student_num)
        TextView tvStudentNum;
        @BindView(R.id.tv_comment_rate)
        TextView tvCommentRate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ivThumb = itemView.findViewById(R.id.iv_thumb);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvStudentNum = itemView.findViewById(R.id.tv_student_num);
            tvCommentRate = itemView.findViewById(R.id.tv_comment_rate);
        }
    }
}
