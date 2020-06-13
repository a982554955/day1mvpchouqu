package com.example.day1mvpchouqu.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.data.BaseInfo;
import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.base.BaseMvpActivity;
import com.example.day1mvpchouqu.interfaces.MyTextWatcher;
import com.example.day1mvpchouqu.model.AccountModel;
import com.yiyatech.utils.newAdd.RegexUtil;
import com.yiyatech.utils.newAdd.SoftInputControl;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import frame.ApiConfig;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import razerdp.design.DialogPopup;

public class RegisterActivity extends BaseMvpActivity<AccountModel> implements DialogPopup.DialogClickCallBack {
    // +86
    @BindView(R.id.telephone_desc)
    TextView telephoneDesc;
    // 账号
    @BindView(R.id.userName)
    EditText userName;
    //验证码
    @BindView(R.id.getVerification)
    TextView getVerification;
    //密码
    @BindView(R.id.password)
    EditText password;
    //下一步
    @BindView(R.id.next_page)
    TextView nextPage;
    @BindView(R.id.title_content)
    TextView titleContent;
    private Disposable mDisposable;
    private DialogPopup mConfirmDialog;

    @Override
    public AccountModel setModel() {
        return new AccountModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_regiester;
    }

    @Override
    public void setUpView() {
        titleContent.setText("填写手机号码");
        nextPage.setEnabled(false);
        password.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onMyTextChanged(CharSequence s, int start, int before, int count) {
                nextPage.setEnabled(s.length()==6?true:false);
            }
        });
    }

    @Override
    public void setUpData() {

    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi){

            case ApiConfig.REGISTER_PHONE:
                BaseInfo info= (BaseInfo) pD[0];
                if (info.isSuccess()){
                    showToast("验证码验证成功");
                    startActivity(new Intent(this,RegisterPhoneActivity.class).putExtra("phoneNum",telephoneDesc.getText().toString() + userName.getText().toString().trim()));
                }else showLog(info.msg);
                break;
            case ApiConfig.CHECK_PHONE_IS_USED:
                BaseInfo checkInfo= (BaseInfo) pD[0];
                if (checkInfo.isSuccess()){
                    mConfirmDialog = new DialogPopup(this, true);
                    mConfirmDialog.setContent(telephoneDesc.getText().toString()+userName.getText().toString()+"\n"+"是否将短信发送到该手机");
                    mConfirmDialog.setDialogClickCallBack(this);
                    mConfirmDialog.showPopupWindow();

                }else showToast("该手机号已注册");
                break;
                case ApiConfig.SEND_REGISTER_VERIFY:
                    BaseInfo sendResult= (BaseInfo) pD[0];
                    if (sendResult.isSuccess()){
                        showToast("验证码发送成功");
                        goTime();

                    }else showLog(sendResult.msg);
                    break;
        }
    }


    @OnClick({R.id.getVerification, R.id.next_page, R.id.back_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.getVerification:
                if (userName.getText()==null){
                    showToast("请输入手机号");
                    return;
                }
                boolean phone = RegexUtil.isPhone(userName.getText().toString().trim());
                if (phone){
                    SoftInputControl.hideSoftInput(this,userName);
                    mPresenter.getData(ApiConfig.CHECK_PHONE_IS_USED,telephoneDesc.getText().toString()+userName.getText().toString().trim());
                }else showToast("手机号格式错误");
                break;
            case R.id.next_page:
                if (userName.getText()!=null){
                    mPresenter.getData(ApiConfig.REGISTER_PHONE,telephoneDesc.getText().toString()+userName.getText().toString().trim(),password.getText().toString().trim());
                }
                break;
            case R.id.back_image:
                finish();
                break;
        }
    }
    private int pTime=60;
    private void goTime() {
        mDisposable = Observable.interval(1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(Long -> {
                    if (pTime - Long > 0) {
                        getVerification.setText(pTime - Long + "");
                    } else {
                        getVerification.setText("获取验证码");
                        disPose();

                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        disPose();
    }
    private void disPose(){
        if (mDisposable!=null&&!mDisposable.isDisposed())mDisposable.dispose();
    }

    @Override
    public void onClick(int type) {
        if (type==mConfirmDialog.OK){
            mPresenter.getData(ApiConfig.SEND_REGISTER_VERIFY,telephoneDesc.getText().toString()+userName.getText().toString().trim());
        }
        mConfirmDialog.dismiss();
    }
}
