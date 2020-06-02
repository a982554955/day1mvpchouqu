package com.example.day1mvpchouqu.base;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.data.Device;
import com.example.day1mvpchouqu.R;
import com.example.frame.FrameApplication;
import com.example.frame.secret.SystemUtils;
import com.yiyatech.utils.NetworkUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 任小龙 on 2020/6/2.
 */
public abstract class BaseSplashActivity extends BaseMvpActivity {
    @BindView(R.id.advert_image)
    public ImageView advertImage;
    @BindView(R.id.time_view)
    public TextView timeView;

    @Override
    public int setLayoutId() {
        return R.layout.activity_splash;
    }

    public void initDevice() {
        Device device = new Device();
        device.setScreenWidth(SystemUtils.getSize(this).x);
        device.setScreenHeight(SystemUtils.getSize(this).y);
        device.setDeviceName(SystemUtils.getDeviceName());
        device.setSystem(SystemUtils.getSystem(this));
        device.setVersion(SystemUtils.getVersion(this));
        device.setDeviceId(SystemUtils.getDeviceId(this));
        device.setLocalIp(NetworkUtils.getLocalIpAddress());
        FrameApplication.getFrameApplication().setDeviceInfo(device);
    }

}
