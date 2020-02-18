package com.example.mylibrary.base;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ConvertUtils;
import com.example.mylibrary.R;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

/**
 */
public abstract class BaseTabLayoutFragment extends BaseFragment  implements OnTabSelectListener {
    public List<Fragment> mFragments ;
    protected PagerAdapter mAdapter;
    protected ViewPager viewPager;
    public SlidingTabLayout tabLayout;
    protected abstract List<Fragment> initFragments() ;
    @Override
    public void onTabReselect(int position) {
        try {
            updateTabTextSize(tabLayout,position);
//            PullRefreshFragment curPullRefreshFragment = getCurPullRefreshFragment();
//            curPullRefreshFragment.scrollToPosition(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //选中字体变大
    public static void updateTabTextSize(SlidingTabLayout viewPagerTab,int position) {
        LinearLayout mTabsContainer = (LinearLayout) viewPagerTab.getChildAt(0);
        for (int i = 0; i < viewPagerTab.getTabCount(); i++) {
            View v = mTabsContainer.getChildAt(i);
            TextView tv_tab_title = v.findViewById(com.flyco.tablayout.R.id.tv_tab_title);
            if (tv_tab_title != null) {
                tv_tab_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, i == position ? ConvertUtils.dp2px(18) :
                        ConvertUtils.dp2px(16));
            }
        }
    }

    @Override
    public void onTabSelect(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public void initViews(View parentView) {
        tabLayout = parentView.findViewById(R.id.tab_layout);
        viewPager = parentView.findViewById(R.id.vp);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        mFragments=initFragments();
        mAdapter =  initFragmentPageAdapter();
    }

    protected abstract FragmentPagerAdapter initFragmentPageAdapter();

    public void setViews(View parentView) {
        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
//                PullRefreshFragment curF = getCurPullRefreshFragment();
//                if (!curF.hasLoadInit()) {
//                    curF.firstLoad();
//                }
            }
        });
        tabLayout.setViewPager(viewPager);
        tabLayout.setOnTabSelectListener(this);
    }


    @Override
    public int getLayoutId() {
        return R.layout.layout_tablayout;
    }

    public  class FlyPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragments = new ArrayList<>();
        private  String[] mTitles ;
        public FlyPagerAdapter(FragmentManager fm, List<Fragment> mFragments, String[] mTitles ) {
            super(fm);
            this.mFragments=mFragments;
            this.mTitles=mTitles;
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

    }
}

