package com.example.day1mvpchouqu.view.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import com.example.day1mvpchouqu.R;
import com.example.day1mvpchouqu.base.BaseMvpActivity;
import com.example.day1mvpchouqu.model.CommonHomeModel;


public class HomeActivity extends BaseMvpActivity<CommonHomeModel> implements NavController.OnDestinationChangedListener {


    public NavController mprejectController;

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
       mprejectController = Navigation.findNavController(this, R.id.project_fragment_control);
        mprejectController.addOnDestinationChangedListener(this);
    }

    @Override
    public void setUpData() {

    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {

    }
    //当我到达某个目的地，就是当我点哪个Fragment
    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        String label = destination.getLabel().toString();
        showLog(label);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
