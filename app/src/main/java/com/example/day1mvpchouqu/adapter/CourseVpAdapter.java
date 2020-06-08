package com.example.day1mvpchouqu.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class CourseVpAdapter extends FragmentStatePagerAdapter {
    private ArrayList<String> tabTitles;
    private ArrayList<Fragment> fragments;

    public CourseVpAdapter(@NonNull FragmentManager fm, ArrayList<String> tabTitles, ArrayList<Fragment> fragments) {
        super(fm);
        this.tabTitles = tabTitles;
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }
}
