package com.example.myapplication.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.example.myapplication.R;
import com.example.mylibrary.base.BaseFragment;
import com.hjq.bar.OnTitleBarListener;

/**
 */
public class ReplyDiscuzFragment extends BaseFragment {
    EditText et_content;

    public static ReplyDiscuzFragment newInstance(Bundle args) {
        ReplyDiscuzFragment fragment = new ReplyDiscuzFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_discuz_reply;
    }

    @Override
    public void initViews(View parentView) {
        et_content = parentView.findViewById(R.id.et_content);
    }

    @Override
    public void setViews(View parentView) {
        titlebar.setTitle("回帖");
        titlebar.setRightTitle("回帖");
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
        String content = et_content.getText().toString();
        if (TextUtils.isEmpty(content)) {
            ToastUtils.showShort("请输入");
            return;
        }
//        User user = new User();
//        user.nickname = input.trim();
//        SPStaticUtils.put(MeFragment.USER, JSON.toJSONString(this.user));
        ToastUtils.showShort("发送成功");
        getActivity().onBackPressed();
    }
}
