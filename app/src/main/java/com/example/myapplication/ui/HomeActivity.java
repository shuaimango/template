package com.example.myapplication.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.ui.fragment.ManhuaTabFragment;
import com.example.myapplication.ui.fragment.MeFragment;
import com.example.myapplication.ui.fragment.DiscuzListFragment;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.item.NormalItemView;


/**
 */

public class HomeActivity extends FragmentActivity {
    protected List<Fragment> mFragments;
    NavigationController navigationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_horizontal);
        ViewPager viewPager = findViewById(R.id.viewPager);
        PageNavigationView tab = findViewById(R.id.tab);
        navigationController = tab.custom()
                .addItem(newItem(R.drawable.ic_home_nor, R.drawable.ic_home_sel, "推荐"))
                .addItem(newItem(R.drawable.ic_home_nor, R.drawable.ic_home_sel, "视频"))
                .addItem(newItem(R.drawable.ic_home_nor, R.drawable.ic_home_sel, "短视频"))
                .addItem(newItem(R.drawable.ic_bbs_nor, R.drawable.ic_bbs_sel, "找车"))
                .addItem(newItem(R.drawable.ic_me_nor, R.drawable.ic_me_sel, "我的"))
                .build();
        mFragments = initFragments();
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragments.get(i);
            }

            @Override
            public int getCount() {
                return navigationController.getItemCount();
            }
        });
        //自动适配ViewPager页面切换
        navigationController.setupWithViewPager(viewPager);
//        //设置消息数
//        navigationController.setMessageNumber(1, 8);
//        //设置显示小圆点
//        navigationController.setHasMessage(0, true);
    }

    //创建一个Item
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        NormalItemView normalItemView = new NormalItemView(this);
        normalItemView.initialize(drawable, checkedDrawable, text);
        normalItemView.setTextDefaultColor(Color.GRAY);
        normalItemView.setTextCheckedColor(getResources().getColor(R.color.app_color));
        return normalItemView;
    }

    protected List<Fragment> initFragments() {
        List<Fragment> mFragments = new ArrayList<>(3);
        mFragments.add(DiscuzListFragment.newInstance(null));
        mFragments.add(DiscuzListFragment.newInstance(null));
        mFragments.add(ManhuaTabFragment.newInstance(null));
        mFragments.add(DiscuzListFragment.newInstance(null));
        mFragments.add(MeFragment.newInstance(null));
        return mFragments;
    }


}
