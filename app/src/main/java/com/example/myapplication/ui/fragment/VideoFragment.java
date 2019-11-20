package com.example.myapplication.ui.fragment;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.example.mylibrary.base.BaseTabLayoutFragment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class VideoFragment extends BaseTabLayoutFragment implements View.OnClickListener {

    public static VideoFragment newInstance(Bundle bundle) {
        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void initViews(View parentView) {
        super.initViews(parentView);
    }

    protected List<Fragment> initFragments() {
        Fragment tab1Fragment = DiscuzListFragment.newInstance(null);
        Fragment tab2Fragment = DiscuzListFragment.newInstance(null);
        Fragment tab3Fragment = DiscuzListFragment.newInstance(null);
        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(tab1Fragment);
        mFragments.add(tab2Fragment);
        mFragments.add(tab3Fragment);
        return mFragments;
    }


    @Override
    protected FragmentPagerAdapter initFragmentPageAdapter() {
        return new FlyPagerAdapter(getChildFragmentManager(), mFragments,
                new String[]{"频道", "明星", "主题"});
    }


    @Override
    public void setViews(View parentView) {
        super.setViews(parentView);
    }

}
