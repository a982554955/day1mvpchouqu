package com.example.day1mvpchouqu.view.fragment;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.adapter.CourseVpAdapter;
import com.example.day1mvpchouqu.base.BaseMvpFragment;
import com.example.day1mvpchouqu.model.MainPageModel;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseFragment extends BaseMvpFragment<MainPageModel> {


    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;



    @Override
    protected int getLayout() {
        return R.layout.fragment_course;
    }

    @Override
    public MainPageModel setModel() {
        return new MainPageModel();
    }

    @Override
    public void setUpView() {
        ArrayList<String> tabTitles = new ArrayList<>();
        tabTitles.add("训练营");
        tabTitles.add("精品课");
        tabTitles.add("公开课");
        ArrayList<Fragment> fragments = new ArrayList<>();
//        fragments.add(new ChildCourseFragment(3));
//        fragments.add(new ChildCourseFragment(1));
//        fragments.add(new ChildCourseFragment(2));
        tablayout.setupWithViewPager(viewpager);
        CourseVpAdapter adapter = new CourseVpAdapter(getChildFragmentManager(),tabTitles,fragments);
        viewpager.setAdapter(adapter);

    }

    @Override
    public void setUpData() {

    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {

    }
}
