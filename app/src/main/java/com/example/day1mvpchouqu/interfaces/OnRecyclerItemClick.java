package com.example.day1mvpchouqu.interfaces;

/**
 * Created by 任小龙 on 2020/6/9.
 */
public interface OnRecyclerItemClick<T> {
    void onItemClick(int pos, T... pTS);
}