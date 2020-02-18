package com.example.myapplication.ui.fragment;

import android.os.Bundle;
import androidx.collection.ArrayMap;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.myapplication.R;
import com.example.myapplication.constant.CommonKey;
import com.example.myapplication.entity.Discuz;
import com.example.myapplication.ui.adapter.DiscuzAdapter;
import com.example.mylibrary.base.BaseRecyclerviewFragment;
import com.example.mylibrary.util.SpaceItemDecoration;
import com.example.myapplication.util.Util_skipPage;
import com.hjq.bar.OnTitleBarListener;

import java.util.ArrayList;
import java.util.List;


/**
 */
public class DiscuzListFragment extends BaseRecyclerviewFragment {
    private static ArrayMap<String, Discuz> map;

    public static DiscuzListFragment newInstance(Bundle args) {
        DiscuzListFragment fragment = new DiscuzListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static ArrayMap<String, Discuz> getMap() {
        if (map == null || map.size() == 0) {
            map = new ArrayMap<>();
            Discuz data1 = new Discuz("1", "猫女王", "有海贼迷吗？哈哈哈，来这报道。", "", "2019-11-05", 726, 87);
            Discuz data2 = new Discuz("2", "只为明天的梦", "性格真的能决定一个人的命运吗？", "", "2019-11-03", 254, 35);
            Discuz data3 = new Discuz("3", "棉绒", "火影忍者里你最喜欢谁？", "", "2019-11-02", 927, 86);
            Discuz data4 = new Discuz("4", "小小5子", "路飞真的会没有烦恼么，会有这种人么。", "", "2019-11-02", 1292, 53);
            Discuz data5 = new Discuz("5", "飞舞的小辣椒", "是什么原因让大家喜欢上漫画的呢？来聊聊吧", "", "2019-11-01", 762, 75);
            Discuz data6 = new Discuz("6", "开心就好2019", "萌宝1个月啦，第一次做妈妈鸭，开心并学习中~", "", "2019-10-31", 827, 22);
            Discuz data7 = new Discuz("7", "夏奇拉", "看了这么就得《名侦探柯南》，柯南今年多大了？", "", "2019-10-30", 231, 12);
            Discuz data8 = new Discuz("8", "春暖花开", "大家去过日本么？觉得日本是个怎样的国家呢？", "", "2019-10-29", 531, 30);
//            Discuz data9 = new Discuz("9", "落花迷人眼", "日本漫画市场不好做啊，中国漫画市场怎么样了呢？", "", "2019-11-28", 1283, 62);
//            Discuz data10 = new Discuz("10", "小喇叭", "你们说人的生活压力咋那大呀？", "", "2019-11-28", 721, 68);
//            Discuz data11 = new Discuz("11", "樱桃在哪里", "说说大家最喜欢的漫画是哪部漫画吧？", "", "2019-11-26", 623, 41);
            map.put(data1.id, data1);
            map.put(data2.id, data2);
            map.put(data3.id, data3);
            map.put(data4.id, data4);
            map.put(data5.id, data5);
            map.put(data6.id, data6);
            map.put(data7.id, data7);
            map.put(data8.id, data8);
//            map.put(data9.id, data9);
//            map.put(data10.id, data10);
//            map.put(data11.id, data11);
        }
        return map;
    }

    @Override
    protected BaseQuickAdapter initAdapter() {
        return new DiscuzAdapter();
    }

    @Override
    protected List parseListResponse(JSONObject jsonObject) {
        String string = SPStaticUtils.getString(MyDiscuzFragment.DiscuzList);
        List<Discuz> list2 = JSON.parseArray(string, Discuz.class);
        if(CollectionUtils.isEmpty(list2)){
            list2=new ArrayList<>();
        }
        ArrayMap<String, Discuz> map = DiscuzListFragment.getMap();
        for (String item:map.keySet() ) {
            list2.add(map.get(item));
        }
        return list2;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Discuz item = (Discuz) adapter.getItem(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable(CommonKey.DATA, item);
        Util_skipPage.startMyFragmentActivity(getActivity(), bundle, DiscuzDetailsFragment.class.getName());
    }

    @Override
    public void initViews(View parentView) {
        super.initViews(parentView);
    }

    @Override
    public void setViews(View parentView) {
        super.setViews(parentView);
        SpaceItemDecoration dividerLine = new SpaceItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL, ConvertUtils.dp2px(7), getResources().getColor(R.color.gray2));
        mRecyclerView.addItemDecoration(dividerLine);
        titlebar.setVisibility(View.VISIBLE);
        titlebar.setLeftIcon(null);
        titlebar.setTitle("论坛");
        titlebar.setRightTitle("发帖");
        titlebar.setOnTitleBarListener(new OnTitleBarListener() {

            @Override
            public void onLeftClick(View v) {
                getActivity().onBackPressed();
            }

            @Override
            public void onTitleClick(View v) {
            }

            @Override
            public void onRightClick(View v) {
                Util_skipPage.startMyFragmentActivity(getActivity(), SendDiscuzFragment.class.getName());
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        refresh();
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
