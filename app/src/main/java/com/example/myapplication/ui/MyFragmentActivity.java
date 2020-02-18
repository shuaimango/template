package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.FragmentUtils;
import com.example.mylibrary.R;
import com.example.mylibrary.base.BaseFragment;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;


/**
 *
 */

public class MyFragmentActivity extends FragmentActivity {
    public static final String CLASSNAME = "CLASSNAME";
    protected Fragment showFragment = null;
    public Bundle extras;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_titlebar);
        initData();
        if (extras != null) {
            TitleBar titlebar = findViewById(R.id.titlebar);
            String title = extras.getString(BaseFragment.TITLE);
            if (!TextUtils.isEmpty(title)) {
                if (extras != null) {
                    titlebar.setTitle(title);
                    titlebar.setOnTitleBarListener(new OnTitleBarListener() {

                        @Override
                        public void onLeftClick(View v) {
                            finish();
                        }

                        @Override
                        public void onTitleClick(View v) {
                        }

                        @Override
                        public void onRightClick(View v) {
                        }
                    });
                }
            } else {
                titlebar.setVisibility(View.GONE);
            }
        }
    }

    protected void initData() {
        try {
            extras = getIntent().getExtras();
            String className = extras.getString(CLASSNAME);
            showFragment = (Fragment) Class.forName(className).newInstance();
            showFragment.setArguments(extras);
            MyFragmentActivity.showFragment(getSupportFragmentManager(), showFragment, R.id.activity_fragment);
//                FragmentManager fm = getSupportFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                if (showFragment.isAdded()) {
//                    ft.show(showFragment);
//                } else {
//                    ft.add(R.id.activity_fragment, showFragment);
//                }
//                ft.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showFragment(FragmentManager fm, Fragment currentFragment, int id) {
        if (currentFragment.isAdded()) {
            FragmentUtils.show(currentFragment);
        } else {
            if (fm.findFragmentByTag(currentFragment.getClass().getSimpleName()) == null) {
                FragmentUtils.add(fm, currentFragment, id, currentFragment.getClass().getSimpleName());
            }
        }
    }
}
