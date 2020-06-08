package com.example.day1mvpchouqu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.data.CourseDrillBean;
import com.example.day1mvpchouqu.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChildCourseRvAdapter extends RecyclerView.Adapter<ChildCourseRvAdapter.ViewHolder> {

    private Context context;
    private List<CourseDrillBean.ResultBean.ListsBean> listsa = new ArrayList<>();
    public ChildCourseRvAdapter(Context pContext, List<CourseDrillBean.ResultBean.ListsBean> pLists) {
        context = pContext;
        listsa = pLists;
    }
    public void initData(List<CourseDrillBean.ResultBean.ListsBean> lists) {
        listsa.addAll(lists);
        notifyDataSetChanged();
    }

    public List<CourseDrillBean.ResultBean.ListsBean> getLists() {
        return listsa;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CourseDrillBean.ResultBean.ListsBean listsBean = listsa.get(position);
        holder.tvLessonName.setText(listsBean.getLesson_name());
        Glide.with(context).load(listsBean.getThumb()).into(holder.imgThumb);
        holder.studentnum.setText(listsBean.getStudentnum() + "人学习");
        holder.tvRate.setText("好评度" + listsBean.getRate() + "%");
        holder.tvPrice.setText("￥" + listsBean.getPrice());
    }

    @Override
    public int getItemCount() {
        return listsa.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_thumb)
        ImageView imgThumb;
        @BindView(R.id.tv_lesson_name)
        TextView tvLessonName;
        @BindView(R.id.img_prople)
        ImageView imgProple;
        @BindView(R.id.studentnum)
        TextView studentnum;
        @BindView(R.id.img_like)
        ImageView imgLike;
        @BindView(R.id.tv_rate)
        TextView tvRate;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
