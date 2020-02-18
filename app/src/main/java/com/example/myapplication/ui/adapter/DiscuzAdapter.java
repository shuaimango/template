package com.example.myapplication.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.entity.Discuz;

public class DiscuzAdapter extends BaseQuickAdapter<Discuz, BaseViewHolder> {

    public DiscuzAdapter() {
        super(R.layout.item_discuz, null);
    }

    @Override
    protected void convert(BaseViewHolder holder, final Discuz item) {
        holder.setText(R.id.tv_userName, item.master_name);//昵称
        holder.setText(R.id.tv_date, item.date);
        holder.setText(R.id.tv_content, item.title);
        holder.setText(R.id.tv_viewNum, item.view_num+"");
        holder.setText(R.id.tv_replyNum, item.msg_num+"");
    }


}
