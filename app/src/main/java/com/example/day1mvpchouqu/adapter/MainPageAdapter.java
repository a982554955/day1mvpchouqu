package com.example.day1mvpchouqu.adapter;

import android.content.Context;
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
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.ViewHolder> {


    private List<IndexCommondEntity> bottomList;
    private List<String> bannerData;
    private List<BannerLiveInfo.Live> mLivesData;
    private Context mContext;
    private final int BANNER = 1, LABLE = 2, LIVE = 3, RIGHT_IMAGE = 4, BIG_IMAGE = 5;

    public MainPageAdapter(List<IndexCommondEntity> pBottomList, List<String> pBannerData, List<BannerLiveInfo.Live> pLivesData, Context pContext) {
        bottomList = pBottomList;
        bannerData = pBannerData;
        mLivesData = pLivesData;
        mContext = pContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        @LayoutRes int layoutId = R.layout.item_homepage_post;
        if (viewType == BANNER) {
            layoutId = R.layout.item_homepage_ad;
        } else if (viewType == LABLE) {
            layoutId = R.layout.item_homepage_shortcuts;
        } else if (viewType == LIVE) {
            layoutId = R.layout.live_recycler_item;
        } else if (viewType == RIGHT_IMAGE) {
            layoutId = R.layout.item_homepage_post;
        } else {
            layoutId = R.layout.main_home_item;
        }
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int type = getItemViewType(position);
        IndexCommondEntity entity = bottomList.get(position);
        switch (type) {
            case BANNER:
                holder.banner.setImages(bannerData).setDelayTime(5000).isAutoPlay(true)
                        .setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {
                                Glide.with(context).load(path).into(imageView);
                            }
                        }).start();

                break;
            case LABLE:

                break;
            case LIVE:

                holder.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
                MainPageChildAdapter adapter = new MainPageChildAdapter(mContext, mLivesData);
                holder.recyclerView.setAdapter(adapter);
                break;
            case RIGHT_IMAGE:

                holder.tvTitle.setText(entity.getTitle());
                holder.tvBrowseNum.setText(entity.getView_num() + "人浏览");
                holder.tvCommentNum.setText(entity.getReply_num() + "人跟帖");
                Glide.with(mContext).load(entity.getPic()).into(holder.ivPhoto);
                holder.tvAuthorAndTime.setText(entity.getDate());
                break;
            case BIG_IMAGE:
                holder.tvTitlem.setText(entity.getTitle());
                Glide.with(mContext).load(entity.getPic()).into(holder.ivPhotom);
                holder.tvTitlem.setText(entity.getTitle() + "");
                holder.tvLikeNum.setText(entity.getRate() + "好评率");
                holder.tvLearnNum.setText(entity.getView_num() + "人学习");
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        int type = RIGHT_IMAGE;
        if (bannerData.size() != 0 && bannerData != null && position == 0) type = BANNER;
        else if (position == 1) type = LABLE;
        else if (mLivesData != null && mLivesData.size() != 0 && position == 2) type = LIVE;
        else {
            if (bottomList != null && bottomList.size() != 0) {
                if (bottomList.get(position).type == 3) {
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
        RecyclerView recyclerView;
        TextView tvTitle;
        ImageView ivPhoto;
        TextView tvLearnNum;
        TextView tvLikeNum;
        TextView tvTitlem;
        Banner banner;
        TextView tvCommentNum;
        TextView tvBrowseNum;
        TextView tvAuthorAndTime;
        ImageView ivPhotom;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView=  itemView.findViewById(R.id.recyclerViewa);
            tvTitle=  itemView.findViewById(R.id.tv_title);
            ivPhoto=  itemView.findViewById(R.id.iv_photo);
            tvLearnNum=  itemView.findViewById(R.id.tv_learn_num);
            tvLikeNum=  itemView.findViewById(R.id.tv_like_num);
            banner=  itemView.findViewById(R.id.banner);
            tvTitlem=  itemView.findViewById(R.id.tv_titlea);
            tvBrowseNum=  itemView.findViewById(R.id.tv_browse_num);
            tvCommentNum=  itemView.findViewById(R.id.tv_comment_num);
            ivPhotom=  itemView.findViewById(R.id.iv_photoa);
            tvAuthorAndTime=  itemView.findViewById(R.id.tv_author_and_time);
        }
    }
}
