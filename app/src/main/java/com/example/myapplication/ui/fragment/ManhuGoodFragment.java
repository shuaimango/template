package com.example.myapplication.ui.fragment;

import android.os.Bundle;
import androidx.collection.ArrayMap;

import com.alibaba.fastjson.JSONObject;
import com.example.myapplication.entity.Manhua;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class ManhuGoodFragment extends ManhuRecommendFragment {
    public static ManhuGoodFragment newInstance(Bundle args) {
        ManhuGoodFragment fragment = new ManhuGoodFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected List parseListResponse(JSONObject jsonObject) {
        ArrayMap<String, Manhua> map = ManhuaTabFragment.getMap();
        List<Manhua> list2 = new ArrayList<>();
        list2.add(map.get("9"));
        list2.add(map.get("10"));
        list2.add(map.get("11"));
        list2.add(map.get("12"));
        return list2;
    }

}
