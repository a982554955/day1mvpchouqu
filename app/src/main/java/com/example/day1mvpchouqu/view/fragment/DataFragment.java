package com.example.day1mvpchouqu.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.adapter.MyFragmentAdapter;
import com.example.day1mvpchouqu.base.BaseFragment;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends BaseFragment {


    @BindView(R.id.tab_layout)
    SegmentTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private Unbinder mBind;
    private List<String> tabTitles = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private MyFragmentAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        mBind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] strings = {"资料小组", "最新精华"};
        Collections.addAll(fragments,DataGroupFragment.newInstance(),RecentilBestFragment.newInstance());
        mAdapter = new MyFragmentAdapter(getChildFragmentManager(), fragments, Arrays.asList(strings));
        viewPager.setAdapter(mAdapter);
        tabLayout.setTabData(strings);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {//被选中
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {//重新被选中

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override   //页面滚动
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override   //页面选中
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override   //当滚动状态发生变化
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBind != null) mBind.unbind();
    }
}
