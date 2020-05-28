package com.example.day1mvpchouqu.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.data.TestInfo;
import com.example.day1mvpchouqu.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHodel> {
    private Context context;
    private List<TestInfo.DataInfo> datas;

    public MainAdapter(Context context, List<TestInfo.DataInfo> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.test_adapter_layout, parent, false);
        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, int position) {
        TestInfo.DataInfo info = datas.get(position);
        ViewHodel viewHodel=holder;
        viewHodel.desc.setText(!TextUtils.isEmpty(info.description)?info.description:!TextUtils.isEmpty(info.author)?info.author:info.title);
        Glide.with(context).load(info.thumbnail).into(viewHodel.leftImage);
        viewHodel.title.setText(info.title);
    }

    @Override
    public int getItemCount() {
        return datas!=null?datas.size():0;
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        ImageView leftImage;
        TextView title;
        TextView desc;
        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            leftImage=  itemView.findViewById(R.id.left_image);
            title=itemView.findViewById(R.id.title_content);
            desc=itemView.findViewById(R.id.desc_content);

        }
    }
}
