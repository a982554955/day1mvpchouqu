package com.example.day1mvpchouqu.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.view.design.BottomTabView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {
    private List<Integer> normalIcon=new ArrayList<>();//未选中的Icon
    private List<Integer>selectedIcon=new ArrayList<>();//选中的Icon
    private List<String> tabContent=new ArrayList<>();//tab对应的内容
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomTabView tabView = getView().findViewById(R.id.bottom_tab);
        Collections.addAll(normalIcon,R.drawable.main_page_view,R.drawable.course_view,R.drawable.vip_view,R.drawable.data_view,R.drawable.mine_view);
        Collections.addAll(selectedIcon,R.drawable.main_selected,R.drawable.course_selected,R.drawable.vip_selected,R.drawable.data_selected,R.drawable.mine_selected);
        Collections.addAll(tabContent,"主页","课程","VIP","资料","我的");
        tabView.setResource(normalIcon,selectedIcon,tabContent,1);
    }
}
