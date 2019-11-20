package com.example.myapplication.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.entity.Discuz;
import com.example.myapplication.entity.Discuz;
import com.example.myapplication.entity.User;
import com.example.mylibrary.base.BaseFragment;
import com.hjq.bar.OnTitleBarListener;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * 我的
 */
public class SendDiscuzFragment extends BaseFragment {
    EditText et_title, et_content;

    public static SendDiscuzFragment newInstance(Bundle args) {
        SendDiscuzFragment fragment = new SendDiscuzFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_discuz_send;
    }

    @Override
    public void initViews(View parentView) {
        et_title = parentView.findViewById(R.id.et_title);
        et_content = parentView.findViewById(R.id.et_content);
    }

    @Override
    public void setViews(View parentView) {
        titlebar.setTitle("发表帖子");
        titlebar.setRightTitle("发帖");
        titlebar.setOnTitleBarListener(new OnTitleBarListener() {

            @Override
            public void onLeftClick(View v) {
                onBackPressedSupport();
            }

            @Override
            public void onTitleClick(View v) {
            }

            @Override
            public void onRightClick(View v) {
                save();
            }
        });
    }

    private void save() {
        String title = et_title.getText().toString();
        String content = et_content.getText().toString();
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) {
            ToastUtils.showShort("请输入");
            return;
        }
        publishDiscuz(MyDiscuzFragment.DiscuzList, title, content);
        ToastUtils.showShort("发帖成功");
        getActivity().onBackPressed();
    }

    public static void publishDiscuz(String key, String title, String content) {
        String string = SPStaticUtils.getString(key);
        List<Discuz> list2 = null;
        if (TextUtils.isEmpty(string)) {
            list2 = new ArrayList<>();
        } else {
            try {
                list2 = JSON.parseArray(string, Discuz.class);
                if (CollectionUtils.isEmpty(list2)) {
                    list2 = new ArrayList<>();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Discuz item = new Discuz();
        item.id = String.valueOf(DiscuzListFragment.getMap().size()+list2.size() + 1);
        item.master_name = MyApplication.getUserInfo().nickname;
        item.title = title;
        item.content = content;
        item.date = TimeUtils.millis2String(System.currentTimeMillis(),"yyyy-MM-dd");
        list2.add(0,item);
        SPStaticUtils.put(key, JSON.toJSONString(list2));
    }
}
