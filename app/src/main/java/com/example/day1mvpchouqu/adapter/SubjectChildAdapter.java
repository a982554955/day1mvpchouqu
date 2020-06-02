package com.example.day1mvpchouqu.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.SpecialtyChooseEntity;
import com.example.day1mvpchouqu.R;
import com.example.frame.FrameApplication;

import java.util.List;


/**
 * Created by 任小龙 on 2020/6/2.
 */
public class SubjectChildAdapter extends RecyclerView.Adapter<SubjectChildAdapter.ViewHolder> {
    private List<SpecialtyChooseEntity.DataBean> data;
    private Context mContext;
    private SubjectAdapter fatherAdapter;

    public SubjectChildAdapter(List<SpecialtyChooseEntity.DataBean> pData, Context pContext, SubjectAdapter fatherAdapter) {
        data = pData;
        mContext = pContext;
        this.fatherAdapter = fatherAdapter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.subject_child_adapter_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.label.setText(data.get(position).getSpecialty_name());
        if (FrameApplication.getFrameApplication().getSelectdInfo() != null && !TextUtils.isEmpty(FrameApplication.getFrameApplication().getSelectdInfo().getSpecialty_id()) && FrameApplication.getFrameApplication().getSelectdInfo().getSpecialty_id().equals(data.get(position).getSpecialty_id())) {
            holder.label.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            holder.label.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_subject_selected));
        } else {
            holder.label.setTextColor(ContextCompat.getColor(mContext, R.color.fontColor333));
            holder.label.setBackground(ContextCompat.getDrawable(mContext, R.drawable.shape_radius_white_bg));
        }
        holder.label.setOnClickListener(v -> {
            FrameApplication.getFrameApplication().setSelectdInfo(data.get(position));
            fatherAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView label;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.subject_label);
        }
    }
}
