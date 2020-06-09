package com.example.day1mvpchouqu.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.data.BannerLiveInfo;
import com.example.data.IndexCommondEntity;
import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.view.design.BannerLayout;
import com.yiyatech.utils.ext.StringUtils;
import com.yiyatech.utils.newAdd.GlideUtil;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.net.wifi.WpsInfo.LABEL;

public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.ViewHolder> {


    private List<IndexCommondEntity> bottomList;
    private List<String> bannerData;
    private List<BannerLiveInfo.Live> mLivesData;
    private Activity mContext;
    private final int BANNER = 1, LADLE = 2, LIVE = 3, RIGHT_IMAGE = 4, BIG_IMAGE = 5;

    public MainPageAdapter(List<IndexCommondEntity> pBottomList, List<String> pBannerData, List<BannerLiveInfo.Live> pLivesData, Activity pContext) {
        bottomList = pBottomList;
        bannerData = pBannerData;
        mLivesData = pLivesData;
        mContext = pContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        @LayoutRes int layoutId ;
        if (viewType == BANNER) {
            layoutId = R.layout.item_homepage_ad;
        } else if (viewType == LADLE) {
            layoutId = R.layout.item_homepage_shortcuts;
        } else if (viewType == LIVE) {
            layoutId = R.layout.live_recycler_item;
        } else if (viewType == BIG_IMAGE) {
            layoutId = R.layout.item_big_image;
        } else {
            layoutId = R.layout.item_homepage_post;
        }
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false),viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            holder.banner.attachActivity(mContext);
            if (bannerData.size() != 0) holder.banner.setViewUrls(bannerData);
        }else if (getItemViewType(position) == LADLE) {

        } else if (getItemViewType(position) == LIVE) {
            LinearLayoutManager manager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
            holder.recyclerView.setLayoutManager(manager);
            LiveAdapter adapter = new LiveAdapter(mLivesData,mContext);
            holder.recyclerView.setAdapter(adapter);
        }  else {
            if (bottomList.size() == 0) return;
            IndexCommondEntity entity = bottomList.get(mLivesData == null || mLivesData.size() == 0 ? position - 2 : position - 3);
            if (getItemViewType(position) == BIG_IMAGE) {
                holder.tvTitle.setText(entity.title);
                GlideUtil.loadCornerImage(holder.image, entity.pic, null, 6f);
                holder.studyNum.setText(entity.getView_num());
                holder.goodPercent.setText(entity.favorite_num);
            } else {
                holder.title.setText(StringUtils.translation(entity.title));
                GlideUtil.loadCornerImage(holder.ivPhoto, entity.pic, null, 6f);
                holder.tvAuthorAndTime.setText(entity.date);
                holder.tvCommentNum.setText((TextUtils.isEmpty(entity.reply_num) ? 0 : entity.reply_num) + "人跟帖");
                holder.tvBrowseNum.setText(entity.view_num + "人浏览");
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        int type = RIGHT_IMAGE;
        if (position == 0) type = BANNER;
        else if (position == 1) type = LADLE;
        else if (mLivesData != null && mLivesData.size() != 0 && position == 2) type = LIVE;
        else {
            int usePos = mLivesData != null && mLivesData.size() != 0 ? position - 3 : position - 2;
            if (bottomList != null && bottomList.size() != 0) {
                if (bottomList.get(usePos).type == 3) {
                    type = RIGHT_IMAGE;
                } else type = BIG_IMAGE;
            }
        }

        return type;
    }

    @Override
    public int getItemCount() {
        return mLivesData != null && mLivesData.size() != 0 ? bottomList.size() + 3 : bottomList.size() + 2;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        BannerLayout banner;

        RecyclerView recyclerView;

        TextView tvTitle;
        ImageView image;
        TextView studyNum;
        TextView goodPercent;

        TextView title;
        TextView tvBrowseNum;
        TextView tvCommentNum;
        ImageView ivPhoto;
        TextView tvAuthorAndTime;
        public ViewHolder(@NonNull View itemView,int type) {
            super(itemView);
            if (type == BANNER) {
                banner = itemView.findViewById(R.id.banner_main);
            } else if (type == LIVE) {
                recyclerView = itemView.findViewById(R.id.recyclerView);
            } else if (type == BIG_IMAGE) {
                tvTitle = itemView.findViewById(R.id.tv_title);
                image = itemView.findViewById(R.id.image);
                studyNum = itemView.findViewById(R.id.study_num);
                goodPercent = itemView.findViewById(R.id.good_percent);
            } else if (type == RIGHT_IMAGE) {
                title = itemView.findViewById(R.id.tv_title_left);
                tvBrowseNum = itemView.findViewById(R.id.tv_browse_num);
                tvCommentNum = itemView.findViewById(R.id.tv_comment_num);
                ivPhoto = itemView.findViewById(R.id.iv_photo);
                tvAuthorAndTime = itemView.findViewById(R.id.tv_author_and_time);
            }
        }
        }
    }

