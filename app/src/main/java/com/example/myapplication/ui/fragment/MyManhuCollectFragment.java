package com.example.myapplication.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ArrayUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.example.myapplication.R;
import com.example.myapplication.entity.Manhua;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class MyManhuCollectFragment extends ManhuRecommendFragment {
    public static final String MyManhuCollect="MyManhuCollect";
    public static MyManhuCollectFragment newInstance(Bundle args) {
        MyManhuCollectFragment fragment = new MyManhuCollectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected List parseListResponse(JSONObject jsonObject) {
        String myManhuaCollects= SPStaticUtils.getString(MyManhuCollectFragment.MyManhuCollect);
        List<Manhua> list2 = JSON.parseArray(myManhuaCollects,Manhua.class);
        return list2;
    }

    @Override
    public void setViews(View parentView) {
        super.setViews(parentView);
        titlebar.setVisibility(View.VISIBLE);
        titlebar.setTitle("我的收藏");
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

}
