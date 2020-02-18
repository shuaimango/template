package com.example.myapplication.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.myapplication.R;
import com.example.myapplication.entity.User;
import com.example.mylibrary.base.BaseFragment;
import com.hjq.bar.OnTitleBarListener;

/**
 * 我的
 */
public class ModifynickNameFragment extends BaseFragment {
    EditText et;
    User  user;
    public static ModifynickNameFragment newInstance(Bundle args) {
        ModifynickNameFragment fragment = new ModifynickNameFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onPause() {
        super.onPause();
        ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_modify_name;
    }

    @Override
    public void initViews(View parentView) {
        et=parentView.findViewById(R.id.et);
    }

    @Override
    public void setViews(View parentView) {
        titlebar.setTitle("昵称");
        titlebar.setRightTitle("保存");
        String userStr = SPStaticUtils.getString(MeFragment.USER);
        if(!TextUtils.isEmpty(userStr)){
              user= JSON.parseObject(userStr,User.class);
            if (user != null&&!TextUtils.isEmpty(user.nickname)) {
                et.setText(user.nickname);
            }
        }
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
                save();
            }
        });
    }

    private void save() {
        String input = et.getText().toString();
        if(TextUtils.isEmpty(input)){
            ToastUtils.showShort("请输入");
            return;
        }
        saveUser( input.trim(),"");
        ToastUtils.showShort("保存成功");
        getActivity().onBackPressed();
    }

    public static void saveUser( String nickname,String avatarUrl) {
        User user = null;
        String userStr = SPStaticUtils.getString(MeFragment.USER);
        if(!TextUtils.isEmpty(userStr)) {
            user = JSON.parseObject(userStr, User.class);
        }else{
            user = new User();
        }
        if(!TextUtils.isEmpty(nickname)) {
            user.nickname=nickname;
        }
        if(!TextUtils.isEmpty(avatarUrl)) {
            user.avatarUrl=avatarUrl;
        }
        SPStaticUtils.put(MeFragment.USER, JSON.toJSONString(user));
    }
}
