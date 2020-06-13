package com.example.day1mvpchouqu.view.activity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

import com.example.data.BaseInfo;
import com.example.data.LoginInfo;
import com.example.data.MainAdEntity;
import com.example.data.SpecialtyChooseEntity;
import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.base.BaseSplashActivity;
import com.example.day1mvpchouqu.model.LauchModel;
import com.yiyatech.utils.newAdd.GlideUtil;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import butterknife.OnClick;
import frame.ApiConfig;
import frame.ICommonModel;
import frame.constants.ConstantKey;
import frame.secret.SystemUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.day1mvpchouqu.constants.JumpConstant.*;

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
        String  string = SharedPrefrenceUtils.getObject(this, ConstantKey.COOKIES);
        mApplication.setCookie(string);
        String specialtyId = "";
        if (mSelectedInfo != null && !TextUtils.isEmpty(mSelectedInfo.getSpecialty_id())) {
            mApplication.setSelectedInfo(mSelectedInfo);
            specialtyId = mSelectedInfo.getSpecialty_id();
        }
        Point realSize = SystemUtils.getRealSize(this);
        mPresenter.getData(ApiConfig.ADVERT, specialtyId, realSize.x, realSize.y);
        new Handler().postDelayed(()->{ if (mInfo == null)jump(); },3000);
        LoginInfo loginInfo = SharedPrefrenceUtils.getObject(this,ConstantKey.LOGIN_INFO);
        if (loginInfo != null && !TextUtils.isEmpty(loginInfo.getUid()))mApplication.setLoginInfo(loginInfo);
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
        if (mSubscribe != null)mSubscribe.dispose();
        Observable.just("我是防抖动").debounce(20,TimeUnit.SECONDS).subscribe(ps->{

            if (mSelectedInfo != null && !TextUtils.isEmpty(mSelectedInfo.getSpecialty_id())){
                if (mApplication.isLogin()){
                    startActivity(new Intent(this,HomeActivity.class));
                }else {
                    startActivity(new Intent(this,LoginActivity.class).putExtra(JUMP_KEY,SPLASH_TO_LOGIN));
                }
            }else {
                startActivity(new Intent(this,SubjectActivity.class).putExtra(JUMP_KEY,SPLASH_TO_SUB));
            }
            finish();

        });


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
