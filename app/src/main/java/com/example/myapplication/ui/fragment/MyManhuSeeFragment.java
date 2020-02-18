package com.example.myapplication.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.SPStaticUtils;
import com.example.myapplication.entity.Manhua;

import java.util.List;


/**
 *
 */
public class MyManhuSeeFragment extends ManhuRecommendFragment {
    public static final String MyManhuSee="MyManhuSee";

    public static MyManhuSeeFragment newInstance(Bundle args) {
        MyManhuSeeFragment fragment = new MyManhuSeeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected List parseListResponse(JSONObject jsonObject) {
        String myManhuaCollects= SPStaticUtils.getString(MyManhuSeeFragment.MyManhuSee);
        List<Manhua> list2 = JSON.parseArray(myManhuaCollects,Manhua.class);
        return list2;
    }
    @Override
    public void setViews(View parentView) {
        super.setViews(parentView);
        titlebar.setVisibility(View.VISIBLE);
        titlebar.setTitle("我的观看");
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
