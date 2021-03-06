package com.example.myapplication.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.widget.RadioGroup;

import com.blankj.utilcode.util.FragmentUtils;
import com.example.myapplication.R;
import com.example.myapplication.entity.Manhua;
import com.example.myapplication.ui.MyFragmentActivity;
import com.example.mylibrary.base.BaseFragment;
import com.example.mylibrary.base.BaseRecyclerviewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class ManhuaTabFragment extends BaseFragment {

    private static ArrayMap<String, Manhua> map;

    protected BaseFragment currentFragment;
    List<BaseFragment> mFragments;
    RadioGroup radioGroup;


    public static ArrayMap<String, Manhua> getMap() {
        if (map == null || map.size() == 0) {
            map = new ArrayMap<>();
            Manhua manhua1 = new Manhua("1", "723", "全职高手", R.drawable.img1, "2019-11-05");
            Manhua manhua2 = new Manhua("2", "364", "斗战狂潮", R.drawable.img2, "2019-11-03");
            Manhua manhua3 = new Manhua("3", "826", "唐寅在异界", R.drawable.img3, "2019-11-03");
            Manhua manhua4 = new Manhua("4", "527", "女神制造系统", R.drawable.img4, "2019-10-31");
            Manhua manhua9 = new Manhua("9", "274", "校园高手", R.drawable.manhua9, "2019-11-10");
            Manhua manhua10 = new Manhua("10", "462", "生命源代码", R.drawable.manhua10, "2019-11-07");
            Manhua manhua11 = new Manhua("11", "522", "最后的召唤师", R.drawable.manhua11, "2019-11-02");
            Manhua manhua12 = new Manhua("12", "826", "灼灼琉璃夏", R.drawable.manhua12, "2019-10-30");
            map.put(manhua1.id, manhua1);
            map.put(manhua2.id, manhua2);
            map.put(manhua3.id, manhua3);
            map.put(manhua4.id, manhua4);
            map.put(manhua9.id, manhua9);
            map.put(manhua10.id, manhua10);
            map.put(manhua11.id, manhua11);
            map.put(manhua12.id, manhua12);
        }
        return map;
    }

    public static ManhuaTabFragment newInstance(Bundle args) {
        ManhuaTabFragment fragment = new ManhuaTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab_manhu;
    }

    @Override
    public void initViews(View parentView) {
        radioGroup = parentView.findViewById(R.id.radioGroup);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        BaseFragment tab1Fragment = ManhuRecommendFragment.newInstance(null);
        BaseFragment tab2Fragment = ManhuGoodFragment.newInstance(null);
        mFragments = new ArrayList<>();
        mFragments.add(tab1Fragment);
        mFragments.add(tab2Fragment);
    }

    @Override
    public void setViews(View parentView) {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment hideFragment = null;
                switch (checkedId) {
                    case R.id.radio0:
                        currentFragment = mFragments.get(0);
                        hideFragment = mFragments.get(1);
                        break;
                    case R.id.radio1:
                        currentFragment = mFragments.get(1);
                        hideFragment = mFragments.get(0);
                        break;
                }
                FragmentUtils.hide(hideFragment);
                MyFragmentActivity.showFragment(getFragmentManager(), currentFragment, R.id.fragment);
//                FragmentUtils.showHide(currentFragment, hideFragment);
                if (currentFragment instanceof BaseRecyclerviewFragment) {
                    ((BaseRecyclerviewFragment) currentFragment).refresh();
                }
            }
        });
        currentFragment = mFragments.get(0);
        MyFragmentActivity.showFragment(getFragmentManager(), currentFragment, R.id.fragment);
    }

}
