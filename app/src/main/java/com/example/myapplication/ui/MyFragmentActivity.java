package com.example.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;

import com.example.mylibrary.R;
import com.example.mylibrary.base.BaseFragment;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportActivity;


/**
 *
 */

public class MyFragmentActivity extends SupportActivity {
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
                            onBackPressedSupport();
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
            if (showFragment instanceof ISupportFragment) {
                if (!showFragment.isAdded()) {
                    loadRootFragment(R.id.activity_fragment, (ISupportFragment) showFragment);
                }
            } else {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                if (showFragment.isAdded()) {
                    ft.show(showFragment);
                } else {
                    ft.add(R.id.activity_fragment, showFragment);
                }
                ft.commitAllowingStateLoss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
