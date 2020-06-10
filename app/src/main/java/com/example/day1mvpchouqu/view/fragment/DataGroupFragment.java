package com.example.day1mvpchouqu.view.fragment;

import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.example.data.BaseInfo;
import com.example.data.DataGroupListEntity;
import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.adapter.DataGroupAdapter;
import com.example.day1mvpchouqu.base.BaseMvpFragment;
import com.example.day1mvpchouqu.interfaces.DataListener;
import com.example.day1mvpchouqu.interfaces.OnRecyclerItemClick;
import com.example.day1mvpchouqu.model.DataModel;
import com.example.day1mvpchouqu.view.activity.LoginActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import frame.ApiConfig;
import frame.FrameApplication;
import frame.LoadTypeConfig;

import static com.example.day1mvpchouqu.adapter.DataGroupAdapter.FOCUS_TYPE;
import static com.example.day1mvpchouqu.adapter.DataGroupAdapter.ITEM_TYPE;
import static com.example.day1mvpchouqu.constants.JumpConstant.*;

public class DataGroupFragment extends BaseMvpFragment<DataModel> implements DataListener, OnRecyclerItemClick {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private List<DataGroupListEntity> mList=new ArrayList<>();
    private int page=1;
    private DataGroupAdapter mAdapter;

    public static DataGroupFragment newInstance() {
        DataGroupFragment fragment = new DataGroupFragment();
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.refresh_list_layout;
    }

    @Override
    public DataModel setModel() {
        return new DataModel();
    }

    @Override
    public void setUpView() {
        initRecyclerView(recyclerView,refreshLayout,this);
        mAdapter = new DataGroupAdapter(mList, getContext());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnRecyclerItemClick(this);
    }

    @Override
    public void setUpData() {
        mPresenter.getData(ApiConfig.DATA_GROUP, LoadTypeConfig.NORMAL,page);
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi){
            case ApiConfig.DATA_GROUP:
                BaseInfo<List<DataGroupListEntity>> info= (BaseInfo<List<DataGroupListEntity>>) pD[0];
                if (info.isSuccess()){
                    List<DataGroupListEntity> result = info.result;
                    int loadMode = (int) pD[1];
                    if (loadMode==LoadTypeConfig.REFRESH){
                        mList.clear();
                        refreshLayout.finishRefresh();
                    }else if (loadMode==LoadTypeConfig.MORE){
                        refreshLayout.finishLoadMore();
                    }
                    mList.addAll(result);
                    mAdapter.notifyDataSetChanged();
                }
                break;
                case ApiConfig.CLICK_CANCEL_FOCUS:
                    BaseInfo baseInfo= (BaseInfo) pD[0];
                    int clickPos= (int) pD[1];
                    if (baseInfo.isSuccess()){
                        showToast("取消成功");
                        mList.get(clickPos).setIs_ftop(0);
                        mAdapter.notifyItemChanged(clickPos);
                    }
                    break;
                    case ApiConfig.CLICK_TO_FOCUS:
                        BaseInfo base= (BaseInfo) pD[0];
                        int clickPosa= (int) pD[1];
                        if (base.isSuccess()){
                            showToast("加入成功");
                            mList.get(clickPosa).setIs_ftop(1);
                            mAdapter.notifyItemChanged(clickPosa);
                        }
                        break;
        }
    }

    @Override
    public void dataType(int mode) {
        if (mode==LoadTypeConfig.REFRESH){
            mPresenter.getData(ApiConfig.DATA_GROUP, LoadTypeConfig.REFRESH,1);
        }else if (mode==LoadTypeConfig.MORE){
            page++;
            mPresenter.getData(ApiConfig.DATA_GROUP, LoadTypeConfig.MORE,page);
        }
    }

    @Override
    public void onItemClick(int pos, Object[] pObjects) {
            if (pObjects!=null&&pObjects.length!=0){
               switch ((int)pObjects[0]){
                   case ITEM_TYPE:

                       break;
                   case FOCUS_TYPE:
                       boolean login = FrameApplication.getFrameApplication().isLogin();
                       if (login){
                           DataGroupListEntity entity = mList.get(pos);
                           if (entity.isFocus()){//已经关注，取消关注
                               mPresenter.getData(ApiConfig.CLICK_CANCEL_FOCUS,entity.getGid(),pos);//绿码
                           } else  {//没有关注，点击关注
                               mPresenter.getData(ApiConfig.CLICK_TO_FOCUS,entity.getGid(),entity.getGroup_name(),pos);
                           }
                       } else {
                           startActivity(new Intent(getContext(), LoginActivity.class).putExtra(JUMP_KEY,DATAGROUPFRAGMENT_TO_LOGIN));
                       }
                       break;
               }
            }
    }
}
