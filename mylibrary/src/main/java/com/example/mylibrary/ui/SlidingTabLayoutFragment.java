//package com.example.mylibrary.ui;
//
//import android.os.Bundle;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentPagerAdapter;
//import androidx.viewpager.widget.PagerAdapter;
//import androidx.viewpager.widget.ViewPager;
//import android.util.TypedValue;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//
//import com.blankj.utilcode.util.ConvertUtils;
//import com.example.mylibrary.R;
//import com.example.mylibrary.base.BaseFragment;
//import com.shizhefei.view.indicator.IndicatorViewPager;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// */
//public abstract class SlidingTabLayoutFragment extends BaseFragment implements OnTabSelectListener {
//    public List<Fragment> mFragments ;
//    protected PagerAdapter mAdapter;
//    protected ViewPager viewPager;
//    private IndicatorViewPager indicatorViewPager;
//    public SlidingTabLayout indicator;
//    protected abstract List<Fragment> initFragments() ;
//    @Override
//    public void onTabReselect(int position) {
//        try {
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void initViews(View parentView) {
//        indicator = parentView.findViewById(R.id.tab_layout);
//        viewPager = parentView.findViewById(R.id.vp);
//    }
//
//    @Override
//    public void initData(Bundle savedInstanceState) {
//        super.initData(savedInstanceState);
//        mFragments=initFragments();
//        mAdapter =  initFragmentPageAdapter();
//    }
//
//    protected abstract FragmentPagerAdapter initFragmentPageAdapter();
//
//    public void setViews(View parentView) {
//        viewPager.setAdapter(mAdapter);
//        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
////                PullRefreshFragment curF = getCurPullRefreshFragment();
////                if (!curF.hasLoadInit()) {
////                    curF.firstLoad();
////                }
//            }
//        });
//        indicator.setViewPager(viewPager);
//        indicator.setOnTabSelectListener(this);
//    }
//
//
//    @Override
//    public int getLayoutId() {
//        return R.layout.layout_tablayout_fly;
//    }
//
//    public  class FlyPagerAdapter extends FragmentPagerAdapter {
//        private List<Fragment> mFragments = new ArrayList<>();
//        private  String[] mTitles ;
//        public FlyPagerAdapter(FragmentManager fm, List<Fragment> mFragments, String[] mTitles ) {
//            super(fm);
//            this.mFragments=mFragments;
//            this.mTitles=mTitles;
//        }
//
//        @Override
//        public int getCount() {
//            return mFragments.size();
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mTitles[position];
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return mFragments.get(position);
//        }
//
//    }
//}
