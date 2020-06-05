package com.example.day1mvpchouqu.view.fragment;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.base.BaseMvpFragment;
import com.example.day1mvpchouqu.model.MainPageModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import frame.ICommonModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainPageFragment extends BaseMvpFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;

    @Override
    protected int getLayout() {
        return R.layout.fragment_main_page;
    }

    @Override
    public ICommonModel setModel() {
        return new MainPageModel();
    }

    @Override
    public void setUpView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
    }

    @Override
    public void setUpData() {

    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {

    }
}
