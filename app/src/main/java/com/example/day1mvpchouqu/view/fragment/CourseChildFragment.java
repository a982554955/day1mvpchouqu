package com.example.day1mvpchouqu.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.BaseInfo;
import com.example.data.CourseListInfo;
import com.example.data.SearchItemEntity;
import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.adapter.CourseChildAdapter;
import com.example.day1mvpchouqu.base.BaseMvpFragment;
import com.example.day1mvpchouqu.interfaces.DataListener;
import com.example.day1mvpchouqu.model.CourseModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import frame.ApiConfig;
import frame.LoadTypeConfig;

public class CourseChildFragment extends BaseMvpFragment<CourseModel> implements DataListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int mIndex;
    private int  page=1;
    private List<SearchItemEntity> mCourseListInfos=new ArrayList<>();
    private CourseChildAdapter mAdapter;

    public static CourseChildFragment getInstance(int index) {
        CourseChildFragment fragment = new CourseChildFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("whichFragment", index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mIndex = (int) getArguments().get("whichFragment");

        }
    }

    @Override
    protected int getLayout() {
        return R.layout.refresh_list_layout;
    }

    @Override
    public CourseModel setModel() {
        return new CourseModel();
    }

    @Override
    public void setUpView() {
        initRecyclerView(recyclerView,refreshLayout,this);
        mAdapter = new CourseChildAdapter(mCourseListInfos, getContext());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setUpData()    {
        mPresenter.getData(ApiConfig.COURSE_CHILD, LoadTypeConfig.NORMAL,mIndex,page);

    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi){
            case ApiConfig.COURSE_CHILD:
                BaseInfo<CourseListInfo> infos= (BaseInfo<CourseListInfo>) pD[0];
                if (infos.isSuccess()){
                    List<SearchItemEntity> lists = infos.result.lists;

                    int loadMode = (int) ((Object[]) pD[1])[0];
                    if (loadMode==LoadTypeConfig.REFRESH){
                        refreshLayout.finishRefresh();
                        mCourseListInfos.clear();
                    }else if (loadMode==LoadTypeConfig.MORE){
                        refreshLayout.finishLoadMore();
                    }
                    mCourseListInfos.addAll(lists);
                    mAdapter.notifyDataSetChanged();
                }


                break;
        }
    }

    @Override
    public void dataType(int mode) {
        if (mode==LoadTypeConfig.REFRESH){
            mPresenter.getData(ApiConfig.COURSE_CHILD, LoadTypeConfig.REFRESH,mIndex,page);

        }else {
            page++;
            mPresenter.getData(ApiConfig.COURSE_CHILD, LoadTypeConfig.MORE,mIndex,page);

        }
    }
}
