package com.example.myapplication.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.SPUtils;
import com.example.myapplication.R;
import com.example.myapplication.entity.User;
import com.example.myapplication.util.ImageLoader;
import com.example.mylibrary.base.BaseFragment;
import com.example.myapplication.util.Util_skipPage;

/**
 *
 */
public class MeFragment extends BaseFragment {
    public static final String USER="USER";
    private ImageView iv_avatar, iv_setting;
    private TextView tv_see, tv_collect,tv_sendTiezi,tv_replyTiezi;
    private User user;

    public static MeFragment newInstance(Bundle args) {
        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_setting:
                Util_skipPage.startMyFragmentActivity(getActivity(), MyInfoFragment.class.getName());
                break;
            case R.id.tv_see:
                Util_skipPage.startMyFragmentActivity(getActivity(), MyManhuSeeFragment.class.getName());
                break;
            case R.id.tv_collect:
                Util_skipPage.startMyFragmentActivity(getActivity(), MyManhuCollectFragment.class.getName());
                break;
            case R.id.tv_sendTiezi:
                Util_skipPage.startMyFragmentActivity(getActivity(), MyDiscuzFragment.class.getName());
                break;
            case R.id.tv_replyTiezi:
                Util_skipPage.startMyFragmentActivity(getActivity(), MyReplyDiscuzFragment.class.getName());
                break;
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public void initViews(View parentView) {
        iv_avatar = parentView.findViewById(R.id.iv_avatar);
        iv_setting = parentView.findViewById(R.id.iv_setting);
        tv_see = parentView.findViewById(R.id.tv_see);
        tv_collect = parentView.findViewById(R.id.tv_collect);
        tv_sendTiezi = parentView.findViewById(R.id.tv_sendTiezi);
        tv_replyTiezi = parentView.findViewById(R.id.tv_replyTiezi);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    public void setViews(View parentView) {
        iv_avatar.setOnClickListener(this);
        iv_setting.setOnClickListener(this);
        tv_see.setOnClickListener(this);
        tv_collect.setOnClickListener(this);
        tv_sendTiezi.setOnClickListener(this);
        tv_replyTiezi.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        String userStr = SPStaticUtils.getString(MeFragment.USER);
        if(!TextUtils.isEmpty(userStr)){
            user=JSON.parseObject(userStr,User.class);
            ImageLoader.displayCircleImg(user.avatarUrl, iv_avatar);
        }
    }
}
