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
import com.example.data.EssencePostEntity;
import com.example.day1mvpchouqu.R;
import com.yiyatech.utils.newAdd.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataEssenceAdapter extends RecyclerView.Adapter<DataEssenceAdapter.ViewHolder> {
   private Context mContext;

    private List<EssencePostEntity> mList = new ArrayList<>();;

    public DataEssenceAdapter(Context pContext, List<EssencePostEntity> pList) {
        mContext = pContext;
        mList = pList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_homepage_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EssencePostEntity entity = mList.get(position);
        Glide.with(mContext).load(entity.getPic()).into(holder.ivPhoto);
        holder.tvAuthorAndTime.setText(TimeUtil.parseTimeYMD(entity.getCreate_time()));
        holder.tvCommentNum.setText(entity.getReply_num()+"人跟帖");
        holder.tvBrowseNum.setText(entity.getView_num()+"人浏览");
        holder.tvTitleLeft.setText(entity.getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title_left)
        TextView tvTitleLeft;
        @BindView(R.id.tv_browse_num)
        TextView tvBrowseNum;
        @BindView(R.id.tv_comment_num)
        TextView tvCommentNum;
        @BindView(R.id.iv_photo)
        ImageView ivPhoto;
        @BindView(R.id.tv_author_and_time)
        TextView tvAuthorAndTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
