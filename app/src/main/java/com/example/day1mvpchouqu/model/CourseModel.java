package com.example.day1mvpchouqu.model;

import com.example.day1mvpchouqu.constants.Method;

import frame.ApiConfig;
import frame.FrameApplication;
import frame.Host;
import frame.ICommonModel;
import frame.ICommonPresenter;
import frame.NetManger;
import frame.constants.Constants;
import frame.utils.ParamHashMap;

public class CourseModel implements ICommonModel {
    private String subjectId=FrameApplication.getFrameApplication().getSelectedInfo().getSpecialty_id();
    @Override
    public void getData(ICommonPresenter pPresenter, int whichApi, Object[] params) {

        switch (whichApi){
            case ApiConfig.COURSE_CHILD:
                ParamHashMap add = new ParamHashMap().add("specialty_id", subjectId).add("page", params[2]).add("limit", Constants.LIMIT_NUM).add("course_type", params[1]);
                NetManger.getInstance().netWork(NetManger.mService.getCourseChildData(Host.EDU_OPENAPI+ Method.GETLESSONLISTFORAPI,add),pPresenter,whichApi,params[0]);
                break;
        }
    }
}
