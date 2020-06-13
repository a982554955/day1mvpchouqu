package com.example.day1mvpchouqu.view.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.data.BaseInfo;
import com.example.data.LoginInfo;
import com.example.data.PersonHeader;
import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.base.BaseMvpActivity;
import com.example.day1mvpchouqu.model.AccountModel;
import com.example.day1mvpchouqu.view.design.LoginView;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import frame.ApiConfig;
import frame.constants.ConstantKey;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.day1mvpchouqu.constants.JumpConstant.JUMP_KEY;
import static com.example.day1mvpchouqu.constants.JumpConstant.REGISTER_TO_LOGIN;
import static com.example.day1mvpchouqu.constants.JumpConstant.SPLASH_TO_LOGIN;
import static com.example.day1mvpchouqu.constants.JumpConstant.SUB_TO_LOGIN;

public class LoginActivity extends BaseMvpActivity<AccountModel> implements LoginView.LoginViewCallBack {
    @BindView(R.id.login_view)
    LoginView mLoginView;
    @BindView(R.id.register_press)
    TextView registerPress;
    private Disposable mSubscribe;
    private String PhoneNum;
    private String mFromType;

    @Override
    public AccountModel setModel() {
        return new AccountModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void setUpView() {
        mFromType = getIntent().getStringExtra(JUMP_KEY);
        mLoginView.setLoginViewCallBack(this);
    }

    @Override
    public void setUpData() {

    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi) {
            case ApiConfig.SEND_VERIFY:
                BaseInfo<String> info = (BaseInfo<String>) pD[0];
                if (info.isSuccess()) {
                    showToast(info.result);
                    goTime();
                } else showToast("验证码发送太频繁，请稍后重试");

                break;

            case ApiConfig.VERIFY_LOGIN:
                case ApiConfig.ACCOUNT_LOGIN:
                BaseInfo<LoginInfo> baseInfo = (BaseInfo<LoginInfo>) pD[0];
                LoginInfo loginInfo = baseInfo.result;
                if (!TextUtils.isEmpty(PhoneNum))loginInfo.login_name = PhoneNum;
                mApplication.setLoginInfo(loginInfo);
                mPresenter.getData(ApiConfig.GET_HEADER_INFO);
                break;
            case ApiConfig.GET_HEADER_INFO:
                PersonHeader personHeader = ((BaseInfo<PersonHeader>) pD[0]).result;
                mApplication.getLoginInfo().personHeader = personHeader;
                SharedPrefrenceUtils.putObject(this, ConstantKey.LOGIN_INFO, mApplication.getLoginInfo());
                jump();
                break;

        }
    }

    private void jump() {
        if (mFromType.equals(SPLASH_TO_LOGIN) || mFromType.equals(SUB_TO_LOGIN))
            startActivity(new Intent(this, HomeActivity.class));
        this.finish();


    }

    private long time = 60l;

    private void goTime() {
        mSubscribe = Observable.interval(1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(goTime -> {
            mLoginView.getVerifyCode.setText(time - goTime + "s");
            if (time - goTime < 1) {
                doPre();
                mLoginView.getVerifyCode.setText("获取验证码");
            }

        });
    }

    private void doPre() {
        if (mSubscribe != null && !mSubscribe.isDisposed()) mSubscribe.dispose();


    }

    @OnClick({R.id.close_login, R.id.register_press, R.id.forgot_pwd, R.id.login_by_qq, R.id.login_by_wx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close_login:
                if (!TextUtils.isEmpty(mFromType) && (mFromType.equals(SUB_TO_LOGIN) || mFromType.equals(SPLASH_TO_LOGIN))||mFromType.equals(REGISTER_TO_LOGIN)) {
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }
                finish();
                break;
            case R.id.register_press:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.forgot_pwd:
                break;
            case R.id.login_by_qq:
                break;
            case R.id.login_by_wx:
                break;
        }
    }

    @Override
    public void sendVerifyCode(String phoneNum) {
        PhoneNum = phoneNum;
        mPresenter.getData(ApiConfig.SEND_VERIFY, PhoneNum);
    }

    @Override
    public void loginPress(int type, String userName, String pwd) {
        doPre();
        if (mLoginView.mCurrentLoginType == mLoginView.VERIFY_TYPE)
            mPresenter.getData(ApiConfig.VERIFY_LOGIN, userName, pwd);
        else if (mLoginView.mCurrentLoginType==mLoginView.ACCOUNT_TYPE){
            mPresenter.getData(ApiConfig.ACCOUNT_LOGIN,userName,pwd);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doPre();
    }




}
