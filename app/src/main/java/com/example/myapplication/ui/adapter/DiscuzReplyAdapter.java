package com.example.myapplication.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.entity.Discuz;
import com.example.myapplication.entity.DiscuzComment;
import com.example.myapplication.ui.fragment.DiscuzListFragment;

public class DiscuzReplyAdapter extends BaseQuickAdapter<DiscuzComment, BaseViewHolder> {

    public DiscuzReplyAdapter() {
        super(R.layout.item_discuz_reply, null);
    }

    @Override
    protected void convert(BaseViewHolder holder, final DiscuzComment item) {
        Discuz discuz = DiscuzListFragment.getMap().get(item.discuzId);
        holder.setText(R.id.tv_discuz_master, item.user_name);
        holder.setText(R.id.tv_discuz_content, discuz==null?"":discuz.title);
        holder.setText(R.id.tv_date, item.date);
        holder.setText(R.id.tv_content, item.content);
    }


}
