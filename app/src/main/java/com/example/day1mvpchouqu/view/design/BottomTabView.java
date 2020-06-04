package com.example.day1mvpchouqu.view.design;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.day1mvpchouqu.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BottomTabView extends RelativeLayout {

    @BindView(R.id.main_page_tab)
    TextView mainPageTab;
    @BindView(R.id.course_tab)
    TextView courseTab;
    @BindView(R.id.vip_tab)
    TextView vipTab;
    @BindView(R.id.data_tab)
    TextView dataTab;
    @BindView(R.id.mine_tab)
    TextView mineTab;
    private Context mContext;
    private final int mTabNum;

    //根据自定义属性中Tab数量的设置，过滤掉不用的View,剩下接下来使用的View
    private List<TextView> usedTabView =new ArrayList<>();
    private List<Integer> normalIcon;//未选中的Icon
    private List<Integer>selectedIcon;//选中的Icon
    private List<String> tabContent;//tab对应的内容
    private int defaultTab=1;//默认显示第几个tab
    private final int mselectedColor;
    private final int munSelectedColor;

    public BottomTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.bottom_tav_view, this);
        ButterKnife.bind(this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BottomTabView, 0, 0);
        mTabNum = ta.getInt(R.styleable.BottomTabView_tabNum, 4);
        mselectedColor = ta.getColor(R.styleable.BottomTabView_selectedColor, Color.RED);
        munSelectedColor = ta.getColor(R.styleable.BottomTabView_unSelectedColor, Color.BLACK);

        Collections.addAll(usedTabView,mainPageTab,courseTab,vipTab,dataTab,mineTab);
        if (mTabNum<5){
            for (int i = 5; i >mTabNum ; i--) {
                usedTabView.get(i-1).setVisibility(GONE);
               usedTabView.remove(i-1);
            }
        }

    }
        /**
         * 给自定义Tab设置数据
         * normalIcon 未选中的icon集合
         * selectedIcon 选中的icon集合
         * tabContent tab内容描述
         * pDefaultTab 默认选中哪个tab
         * */
    public void setResource(List<Integer> normalIcon,List<Integer>selectedIcon,List<String> tabContent,int pDefaultTab){
        if (pDefaultTab<=0){
            Log.e(this.getClass().getName(),"setResource"+"0个tab 你玩啥呢");
        }
        if (usedTabView.size()!=normalIcon.size()|| usedTabView.size()!=selectedIcon.size()|| usedTabView.size()!=tabContent.size()){
            Log.e(this.getClass().getName(),"--------"+"自定义属性的数量和传递的资源数量不匹配");
            return;
        }
        this.normalIcon=normalIcon;this.selectedIcon=selectedIcon;this.tabContent=tabContent;
        defaultTab=pDefaultTab;
        setContext();
       setStyle();
    }

    private void setContext() {
        for (int i = 0; i <tabContent.size() ; i++) {
            usedTabView.get(i).setText(tabContent.get(i));
        }
    }

    private void setStyle() {
        for (int i = 0; i <usedTabView.size() ; i++) {
            if (i==defaultTab-1){
                usedTabView.get(i).setTextColor(mselectedColor);

                usedTabView.get(i).setCompoundDrawablesWithIntrinsicBounds(0,selectedIcon.get(i),0,0);

            }else {
                usedTabView.get(i).setTextColor(mselectedColor);
                usedTabView.get(i).setCompoundDrawablesWithIntrinsicBounds(0,normalIcon.get(i),0,0);
            }
        }
    }

    @OnClick({R.id.main_page_tab, R.id.course_tab, R.id.vip_tab, R.id.data_tab, R.id.mine_tab})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.main_page_tab) {
            defaultTab = 1;
        } else if (id == R.id.course_tab) {
            defaultTab = 2;
        } else if (id == R.id.vip_tab) {
            defaultTab = 3;
        } else if (id == R.id.data_tab) {
            defaultTab = 4;
        } else if (id == R.id.mine_tab) {
            defaultTab = 5;
        }
        setStyle();
    }
}
