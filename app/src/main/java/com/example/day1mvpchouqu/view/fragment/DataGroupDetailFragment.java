package com.example.day1mvpchouqu.view.fragment;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.BaseInfo;
import com.example.data.GroupDetailEntity;
import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.adapter.DataGroupDateilBottomAdapter;
import com.example.day1mvpchouqu.adapter.GroupDetailCenterTabAdapter;
import com.example.day1mvpchouqu.base.BaseMvpFragment;
import com.example.day1mvpchouqu.interfaces.DataListener;
import com.example.day1mvpchouqu.model.DataModel;
import com.example.day1mvpchouqu.view.activity.HomeActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yiyatech.utils.newAdd.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import frame.ApiConfig;
import frame.LoadTypeConfig;
import frame.constants.ConstantKey;
import frame.constants.Constants;
import frame.utils.ParamHashMap;

public class DataGroupDetailFragment extends BaseMvpFragment<DataModel> implements DataListener {

    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.iv_thumb)
    ImageView ivThumb;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_member_num)
    TextView tvMemberNum;
    @BindView(R.id.tv_post_num)
    TextView tvPostNum;
    @BindView(R.id.tv_focus)
    TextView tvFocus;
    @BindView(R.id.groupBack)
    ImageView groupBack;
    @BindView(R.id.groupTitle)
    TextView groupTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabRecycle)
    RecyclerView tabRecycle;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private String mGid;
    private List<GroupDetailEntity.Tag> mTabListData = new ArrayList<>();
    private List<GroupDetailEntity.Thread> mBottomData = new ArrayList<>();
    private DataGroupDateilBottomAdapter mDataGroupDateilBottomAdapter;
    private GroupDetailCenterTabAdapter mGroupDetailCenterTabAdapter;
    private HomeActivity mHomeActivity;

    @Override
    protected int getLayout() {
        return R.layout.fragment_data_group_detail;
    }

    @Override
    public DataModel setModel() {
        return new DataModel();
    }

    @Override
    public void setUpView() {
        mHomeActivity = (HomeActivity) getActivity();
        if (getArguments() != null) {
            mGid = getArguments().getString(ConstantKey.GROU_TO_DETAIL_GID);
        }
        groupTitle.setVisibility(View.GONE);
        appBar.addOnOffsetChangedListener((pAppBarLayout, verticalOffset) -> {
            boolean space = Math.abs(verticalOffset) >= tvName.getBottom();
            groupTitle.setVisibility(space ? View.VISIBLE : View.GONE);
            toolbar.setBackgroundColor(space ? setColor(R.color.app_theme) : Color.TRANSPARENT);

        });
        initRecyclerView(recyclerView, refreshLayout, this);
        mDataGroupDateilBottomAdapter = new DataGroupDateilBottomAdapter(getContext(), mBottomData);
        recyclerView.setAdapter(mDataGroupDateilBottomAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        tabRecycle.setLayoutManager(manager);
        mGroupDetailCenterTabAdapter = new GroupDetailCenterTabAdapter(getContext(), mTabListData);
        tabRecycle.setAdapter(mGroupDetailCenterTabAdapter);
    }

    @Override
    public void setUpData() {
        mPresenter.getData(ApiConfig.GROUP_DETAIL, mGid);
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi) {
            case ApiConfig.GROUP_DETAIL:
                BaseInfo<GroupDetailEntity> baseInfo = (BaseInfo<GroupDetailEntity>) pD[0];
                if (baseInfo.isSuccess()) {
                    GroupDetailEntity groupDetailEntity = baseInfo.result;
                    setDetailData(groupDetailEntity);
                    getFooterData(LoadTypeConfig.NORMAL);
                }
                break;
            case ApiConfig.GROUP_DETAIL_FOOTER_DATA:
                BaseInfo<GroupDetailEntity> info = (BaseInfo<GroupDetailEntity>) pD[0];
                if (info.isSuccess()) {
                    int loadType = (int) pD[1];
                    if (loadType == LoadTypeConfig.REFRESH) {
                        refreshLayout.finishRefresh();
                        mBottomData.clear();
                    } else if (loadType == LoadTypeConfig.MORE) {
                        refreshLayout.finishLoadMore();
                        if (info.result.getThread_list().size() < Constants.LIMIT_NUM)
                            refreshLayout.setNoMoreData(true);
                    }
                    mBottomData.addAll(info.result.getThread_list());
                    mDataGroupDateilBottomAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    private int page = 1;
    private String tags = "";

    private void getFooterData(int pNormal) {
        ParamHashMap add = new ParamHashMap().add("gid", mGid).add("page", page).add("limit", Constants.LIMIT_NUM);
        if (!TextUtils.isEmpty(tags)) add.add("tagall", tags);
        mPresenter.getData(ApiConfig.GROUP_DETAIL_FOOTER_DATA, pNormal, add);
    }

    private void setDetailData(GroupDetailEntity pGroupDetailEntity) {

        GroupDetailEntity.Group groupInner = pGroupDetailEntity.getGroupinfo();
        tvName.setText(groupInner.getGroup_name());
        groupTitle.setText(groupInner.getGroup_name());
        tvMemberNum.setText("成员 " + groupInner.getMember_num() + " 人");
        tvPostNum.setText("资料 " + groupInner.getThread_num() + " 篇");
        tvFocus.setText(groupInner.getIs_add() == 1 ? "已关注" : "关注");
        GlideUtil.loadCornerImage(ivThumb, groupInner.getLogo(), null, 10);
        GlideUtil.loadBlurredBackground(groupInner.getLogo(), imageBack);
        mTabListData.addAll(pGroupDetailEntity.getTag_arr());
        mGroupDetailCenterTabAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.groupBack)
    public void onViewClicked() {
        mHomeActivity.mprejectController.navigateUp();
    }

    @Override
    public void dataType(int mode) {
        page = mode == LoadTypeConfig.REFRESH ? 1 : page + 1;
        getFooterData(mode);
    }
}
