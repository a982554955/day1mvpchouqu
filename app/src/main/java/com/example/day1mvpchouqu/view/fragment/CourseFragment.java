package com.example.day1mvpchouqu.view.fragment;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.adapter.MyFragmentAdapter;
import com.example.day1mvpchouqu.base.BaseMvpFragment;
import com.example.day1mvpchouqu.model.MainPageModel;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseFragment extends BaseMvpFragment {


    @BindView(R.id.slide_layout)
    SlidingTabLayout slideLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
   private List<String> tabTitles = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private MyFragmentAdapter mAdapter;

    public static final int TRAINTAB = 3;
    public static final int BESTTAB = 1;
    public static final int PUBLICTAB = 2;

    @Override
    protected int getLayout() {
        return R.layout.fragment_course;
    }

    @Override
    public MainPageModel setModel() {
        return null;
    }

    @Override
    public void setUpView() {
        mAdapter = new MyFragmentAdapter(getChildFragmentManager(),  fragments,tabTitles);
        viewPager.setAdapter(mAdapter);
        slideLayout.setViewPager(viewPager);
    }

    @Override
    public void setUpData() {

        Collections.addAll(tabTitles,"训练营","精品课","公开课");
        Collections.addAll(fragments,CourseChildFragment.getInstance(TRAINTAB),CourseChildFragment.getInstance(BESTTAB),CourseChildFragment.getInstance(PUBLICTAB));
        mAdapter.notifyDataSetChanged();
        slideLayout.notifyDataSetChanged();

    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {

    }
}
