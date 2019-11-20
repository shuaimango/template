package com.example.myapplication.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.SPStaticUtils;
import com.example.myapplication.constant.ServerUrl;
import com.example.myapplication.entity.Discuz;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class MyDiscuzFragment extends DiscuzListFragment {
    public static final String DiscuzList = "DiscuzList";

    public static MyDiscuzFragment newInstance(Bundle args) {
        MyDiscuzFragment fragment = new MyDiscuzFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected List parseListResponse(JSONObject jsonObject) {
        String string = SPStaticUtils.getString(MyDiscuzFragment.DiscuzList);
        List<Discuz> list2 = JSON.parseArray(string, Discuz.class);
        return list2;
    }

    @Override
    public void setViews(View parentView) {
        super.setViews(parentView);
        titlebar.setLeftIcon(com.example.mylibrary.R.mipmap.icon_back);
        titlebar.setTitle("我的发帖");
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
