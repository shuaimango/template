package com.example.myapplication.ui.fragment;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.myapplication.R;
import com.example.myapplication.constant.ServerUrl;
import com.example.myapplication.entity.Manhua;
import com.example.myapplication.ui.MyWebViewFragment;
import com.example.myapplication.ui.adapter.ManhuaAdapter;
import com.example.mylibrary.base.BaseRecyclerviewFragment;
import com.example.mylibrary.ui.WebViewTitleFragment;
import com.example.mylibrary.util.SpaceItemDecoration;
import com.example.myapplication.util.Util_skipPage;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 *
 */
public class ManhuRecommendFragment extends BaseRecyclerviewFragment {
    public static ManhuRecommendFragment newInstance(Bundle args) {
        ManhuRecommendFragment fragment = new ManhuRecommendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected BaseQuickAdapter initAdapter() {
        return new ManhuaAdapter();
    }

    @Override
    protected List parseListResponse(JSONObject jsonObject) {
        ArrayMap<String, Manhua> map = ManhuaTabFragment.getMap();
        List<Manhua> list2 = new ArrayList<>();
        list2.add(map.get("1"));
        list2.add(map.get("2"));
        list2.add(map.get("3"));
        list2.add(map.get("4"));
        return list2;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Manhua item = (Manhua) adapter.getItem(position);
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, item.title);
        bundle.putString(WebViewTitleFragment.URL, "http://cdn-img.xj8.live/html5/cartoon/cartoon.html?id=" + item.id);
        Util_skipPage.startMyFragmentActivity(getActivity(), bundle, MyWebViewFragment.class.getName());
        MyWebViewFragment.saveManhua(MyManhuSeeFragment.MyManhuSee,item.id);
    }

    @Override
    public void initViews(View parentView) {
        super.initViews(parentView);
    }

    @Override
    public void setViews(View parentView) {
        super.setViews(parentView);
        SpaceItemDecoration dividerLine = new SpaceItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL, ConvertUtils.dp2px( 7), getResources().getColor(R.color.gray2));
        mRecyclerView.addItemDecoration(dividerLine);
    }



    @Override
    public void refresh() {
        mRefreshLayout.autoRefresh();
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.setNewData(parseListResponse(null));
                mRefreshLayout.finishRefresh();
            }
        },2000);
    }

}
