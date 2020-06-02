package com.example.day1mvpchouqu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.SpecialtyChooseEntity;
import com.example.day1mvpchouqu.R;
import com.yiyatech.utils.newAdd.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {

    private Context context;
    private List<SpecialtyChooseEntity> mList;

    public SubjectAdapter(Context context, List<SpecialtyChooseEntity> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.subject_child_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SpecialtyChooseEntity data = mList.get(position);
        GlideUtil.loadRoundImage(holder.leftRoundImage,data.getIcon());
        holder.itemTitle.setText(data.getBigspecialty());
        holder.itemRecyclerview.setLayoutManager(new GridLayoutManager(context,4));

        holder.itemRecyclerview.setAdapter(new SubjectChildAdapter(data.getData(),context,this));
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.left_round_image)
        ImageView leftRoundImage;
        @BindView(R.id.item_title)
        TextView itemTitle;
        @BindView(R.id.item_recyclerview)
        RecyclerView itemRecyclerview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            
        }
    }
}
