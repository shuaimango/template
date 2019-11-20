package com.example.myapplication.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.entity.DiscuzComment;

public class DiscuzCommentAdapter extends BaseQuickAdapter<DiscuzComment, BaseViewHolder> {

    public DiscuzCommentAdapter() {
        super(R.layout.item_discuz_comment, null);
    }

    @Override
    protected void convert(BaseViewHolder holder, final DiscuzComment item) {
        TextView tv_master = holder.getView(R.id.tv_master);
        tv_master.setVisibility(holder.getLayoutPosition()==1?View.VISIBLE:View.INVISIBLE);
        holder.setText(R.id.tv_name, item.user_name);//昵称
        holder.setText(R.id.tv_content, item.content);
        holder.setText(R.id.tv_date, item.date);//昵称
    }


}
