package com.example.day1mvpchouqu.view.activity;

import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.data.BaseInfo;
import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.base.BaseMvpActivity;
import com.example.day1mvpchouqu.interfaces.MyTextWatcher;
import com.example.day1mvpchouqu.model.AccountModel;
import com.example.day1mvpchouqu.utils.CheckUserNameAndPwd;

import butterknife.BindView;
import butterknife.OnClick;
import frame.ApiConfig;

import static com.example.day1mvpchouqu.constants.JumpConstant.JUMP_KEY;
import static com.example.day1mvpchouqu.constants.JumpConstant.REGISTER_TO_LOGIN;


public class RegisterPhoneActivity extends BaseMvpActivity<AccountModel> {

    //标题
    @BindView(R.id.title_content)
    TextView titleContent;
    //清除用户名
    @BindView(R.id.clearAccount)
    ImageView clearAccount;
    //输入用户名
    @BindView(R.id.accountContent)
    EditText accountContent;
    //密码可见或不可见
    @BindView(R.id.visibleImage)
    ImageView visibleImage;
    //输入密码
    @BindView(R.id.accountSecret)
    EditText accountSecret;
    //下一步的按钮
    @BindView(R.id.next_page)
    TextView nextPage;
    private String mPhoneNum;

    @Override
    public AccountModel setModel() {
        return new AccountModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_register_phone;
    }

    @Override
    public void setUpView() {
        titleContent.setText("创建账号");
        mPhoneNum = getIntent().getStringExtra("phoneNum");
        nextPage.setSelected(false);
        accountContent.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onMyTextChanged(CharSequence s, int start, int before, int count) {
                clearAccount.setVisibility(s.length() > 0 ? View.VISIBLE : View.INVISIBLE);
                nextPage.setSelected(s.length() > 0 && accountSecret.getText().length() > 0);

            }
        });
        accountSecret.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onMyTextChanged(CharSequence s, int start, int before, int count) {
                visibleImage.setVisibility(s.length() > 0 ? View.VISIBLE : View.INVISIBLE);
                nextPage.setSelected(s.length() > 0 && accountContent.getText().length() > 0);
            }
        });

    }

    @Override
    public void setUpData() {

    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi) {
            case ApiConfig.NET_CHECK_USERNAME:
                BaseInfo baseInfo = (BaseInfo) pD[0];
                if (baseInfo.isSuccess()) {
                    mPresenter.getData(ApiConfig.COMPLETE_REGISTER_WITH_SUBJECT, accountContent.getText().toString().trim(), accountSecret.getText().toString().trim(), mPhoneNum);

                } else if (baseInfo.errNo == 114) {
                    showToast("该用户名已经被注册");
                } else showToast(baseInfo.msg);
                break;
            case ApiConfig.COMPLETE_REGISTER_WITH_SUBJECT:
                BaseInfo info1 = (BaseInfo) pD[0];
                if (info1.isSuccess() || info1.errNo == 24 || info1.errNo == 114) {
                    showToast("注册成功");
                    startActivity(new Intent(this, LoginActivity.class).putExtra(JUMP_KEY, REGISTER_TO_LOGIN));
                } else showToast(info1.msg);
                break;
        }
    }


    @OnClick({R.id.back_image, R.id.clearAccount, R.id.visibleImage, R.id.next_page})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_image:
                finish();
                break;
            case R.id.clearAccount:
                accountContent.setText("");
                break;
            case R.id.visibleImage:
                accountSecret.setTransformationMethod(visibleImage.isSelected() ? PasswordTransformationMethod.getInstance() : HideReturnsTransformationMethod.getInstance());
                visibleImage.setSelected(!visibleImage.isSelected());
                break;
            case R.id.next_page:
                if (CheckUserNameAndPwd.verificationUsername(this, accountContent.getText().toString(), accountSecret.getText().toString()))
                    mPresenter.getData(ApiConfig.NET_CHECK_USERNAME, accountContent.getText().toString().trim());
                break;
        }
    }
}
