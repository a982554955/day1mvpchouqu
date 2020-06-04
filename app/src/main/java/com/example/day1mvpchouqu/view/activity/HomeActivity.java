package com.example.day1mvpchouqu.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.base.BaseMvpActivity;
import com.example.day1mvpchouqu.model.CommonHomeModel;


public class HomeActivity extends BaseMvpActivity<CommonHomeModel> {


    public NavController prejectController;

    @Override
    public CommonHomeModel setModel() {
        return new CommonHomeModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void setUpView() {
        prejectController = Navigation.findNavController(this, R.id.project_fragment_control);
    }

    @Override
    public void setUpData() {

    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {

    }
}
