package com.example.myapplication.ui.fragment;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.myapplication.R;
import com.example.myapplication.constant.CommonKey;
import com.example.myapplication.entity.DiscuzComment;
import com.example.myapplication.ui.adapter.DiscuzReplyAdapter;
import com.example.myapplication.util.Util_skipPage;
import com.example.mylibrary.base.BaseRecyclerviewFragment;
import com.example.mylibrary.util.SpaceItemDecoration;

import java.util.List;


/**
 *
 */
public class MyReplyDiscuzFragment extends BaseRecyclerviewFragment {
    public static final String MyReplyDiscuz = "MyReplyDiscuz";

    public static MyReplyDiscuzFragment newInstance(Bundle args) {
        MyReplyDiscuzFragment fragment = new MyReplyDiscuzFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public MyReplyDiscuzFragment() {

    }

    @Override
    protected BaseQuickAdapter initAdapter() {
        return new DiscuzReplyAdapter();
    }

    @Override
    protected List parseListResponse(JSONObject jsonObject) {
        String string = SPStaticUtils.getString(MyReplyDiscuzFragment.MyReplyDiscuz);
        List<DiscuzComment> list2 = JSON.parseArray(string, DiscuzComment.class);
        return list2;
    }
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        DiscuzComment item = (DiscuzComment) adapter.getItem(position);
        Bundle bundle = new Bundle();
        bundle.putString(CommonKey.ID,item.discuzId);
        Util_skipPage.startMyFragmentActivity(getActivity(), bundle,DiscuzDetailsFragment.class.getName());
    }

    @Override
    public void initViews(View parentView) {
        super.initViews(parentView);
    }

//    @Override
//    public int getLayoutId() {
//        return R.layout.fragment_videostar;
//    }

    @Override
    public void setViews(View parentView) {
        super.setViews(parentView);
        SpaceItemDecoration dividerLine = new SpaceItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL, ConvertUtils.dp2px( 7), getResources().getColor(R.color.gray2));
        mRecyclerView.addItemDecoration(dividerLine);
        titlebar.setVisibility(View.VISIBLE);
        titlebar.setTitle("我的跟帖");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.tv_countryIn:
//                TabTagAdapter.selectItem(true, tv_countryIn);
//                TabTagAdapter.selectItem(false, tv_countryOut);
//                break;
        }
    }


    @Override
    public void refresh() {
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.setNewData(parseListResponse(null));
                mRefreshLayout.finishRefresh();
            }
        },2000);
    }

}
