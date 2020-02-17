package com.example.myapplication.ui.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.entity.Manhua;

public class ManhuaAdapter extends BaseQuickAdapter<Manhua, BaseViewHolder> {
    int screenWidth;

    public ManhuaAdapter() {
        super(R.layout.item_manhua, null);
    }

    @Override
    protected void convert(BaseViewHolder holder, final Manhua item) {
        ImageView imageViewCoverImage = holder.getView(R.id.iv_cover);
        TextView tv_date = holder.getView(R.id.tv_date);
        TextView tv_zan = holder.getView(R.id.tv_zan);
        Glide.with(MyApplication.mContext)
                .load( item.cover)
                .into(imageViewCoverImage);
        android.view.ViewGroup.LayoutParams lp = imageViewCoverImage.getLayoutParams();
        screenWidth=ScreenUtils.getScreenWidth();
        lp.height =  (screenWidth *195/350);
        imageViewCoverImage.setLayoutParams(lp);
        holder.setText(R.id.tv_name, item.title);//昵称
        tv_date.setText(item.date);
        tv_zan.setText(item.num);
    }


}
