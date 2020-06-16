package com.example.day1mvpchouqu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.GroupDetailEntity;
import com.example.day1mvpchouqu.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupDetailCenterTabAdapter extends RecyclerView.Adapter<GroupDetailCenterTabAdapter.ViewHolder> {

    private Context mContext;
    private List<GroupDetailEntity.Tag> mTabListData;

    public GroupDetailCenterTabAdapter(Context pContext, List<GroupDetailEntity.Tag> pTabListData) {
        mContext = pContext;
        mTabListData = pTabListData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.group_detail_tab_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GroupDetailEntity.Tag tag = mTabListData.get(position);
        holder.tagContent.setText(tag.getTag_name());
        holder.fallsView.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return mTabListData != null ? mTabListData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.falls_view)
        View fallsView;
        @BindView(R.id.tagContent)
        TextView tagContent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
