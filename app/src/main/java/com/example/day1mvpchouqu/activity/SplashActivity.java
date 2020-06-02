package com.example.day1mvpchouqu.activity;

import android.content.Intent;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

import com.example.data.BaseInfo;
import com.example.data.MainAdEntity;
import com.example.data.SpecialtyChooseEntity;
import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.base.BaseSplashActivity;
import com.example.day1mvpchouqu.model.LauchModel;
import com.example.frame.ApiConfig;
import com.example.frame.ICommonModel;
import com.example.frame.constants.ConstantKey;
import com.example.frame.secret.SystemUtils;
import com.yiyatech.utils.newAdd.GlideUtil;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import java.util.concurrent.TimeUnit;

import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends BaseSplashActivity {

    private BaseInfo<MainAdEntity> mInfo;
    private Disposable mSubscribe;
    private SpecialtyChooseEntity.DataBean mSelectedInfo;

    @Override
    public ICommonModel setModel() {
        return new LauchModel();
    }

    @Override
    public void setUpView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initDevice();
    }

    @Override
    public void setUpData() {
        mSelectedInfo = SharedPrefrenceUtils.getObject(this, ConstantKey.SUBJECT_SELECT);
        String specialtyId = "";
        if (mSelectedInfo != null && !TextUtils.isEmpty(mSelectedInfo.getSpecialty_id())) {
            mAppication.setSelectdInfo(mSelectedInfo);
            specialtyId = mSelectedInfo.getSpecialty_id();
        }
        Point realSize = SystemUtils.getRealSize(this);
        commonPresenter.getData(ApiConfig.ADVERT, specialtyId, realSize.x, realSize.y);
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        mInfo = (BaseInfo<MainAdEntity>) pD[0];
        GlideUtil.loadImage(advertImage, mInfo.result.getInfo_url());
        timeView.setVisibility(View.VISIBLE);
        goTime();
    }

    private int preTime = 4;

    private void goTime() {
        mSubscribe = Observable.interval(1, TimeUnit.SECONDS).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(pLong -> {
                    if (preTime - pLong > 0) timeView.setText(preTime - pLong + "s");
                    else jump();
                });
    }

    private void jump() {
        mSubscribe.dispose();
        startActivity(new Intent(this,mSelectedInfo != null && !TextUtils.isEmpty(mSelectedInfo.getSpecialty_id()) ? mAppication.isLogin() ? HomeActivity.class : LoginActivity.class : SubjectActivity.class ));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscribe != null && !mSubscribe.isDisposed()) mSubscribe.dispose();
    }

    @OnClick({R.id.advert_image, R.id.time_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.advert_image:
                if (mInfo != null) {
//                    mInfo.result.getJump_url();
                }
                break;
            case R.id.time_view:
                jump();
                break;
        }
    }
}
