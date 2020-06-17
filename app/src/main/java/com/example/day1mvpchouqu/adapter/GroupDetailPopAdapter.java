package com.example.day1mvpchouqu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.GroupDetailEntity;
import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.interfaces.OnRecyclerItemClick;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupDetailPopAdapter extends RecyclerView.Adapter<GroupDetailPopAdapter.ViewHolder> {

    private Context mContext;
    private List<GroupDetailEntity.Tag.SelectsBean> mPopData;
    private List<String> mContains;

    public GroupDetailPopAdapter(Context pContext, List<GroupDetailEntity.Tag.SelectsBean> pPopData, List<String> pContains) {
        mContext = pContext;
        mPopData = pPopData;
        mContains = pContains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.group_detail_pop_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GroupDetailEntity.Tag.SelectsBean bean = mPopData.get(position);
        holder.tabPopItem.setText(bean.getName());
        holder.tabPopItem.setTextColor(ContextCompat.getColor(mContext, mContains.contains(bean.getName()) ? R.color.white : R.color.black));
        holder.tabPopItem.setBackground(ContextCompat.getDrawable(mContext, mContains.contains(bean.getName()) ? R.drawable.shape_group_pop_item_bg : R.drawable.shape_group_pop_item_bg_unselected));
        holder.tabPopItem.setOnClickListener(v -> {
            if (mOnRecyclerItemClick != null) mOnRecyclerItemClick.onItemClick(position);
        });
    }
    private OnRecyclerItemClick mOnRecyclerItemClick;

    public void setOnRecyclerItemClick(OnRecyclerItemClick pOnRecyclerItemClick) {
        mOnRecyclerItemClick = pOnRecyclerItemClick;
    }
    @Override
    public int getItemCount() {
        return mPopData!=null?mPopData.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tabPopItem)
        TextView tabPopItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
