//package com.example.myapplication.ui.fragment;
//
//import android.os.Build;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Toast;
//
//import com.avos.avoscloud.AVException;
//import com.avos.avoscloud.AVObject;
//import com.avos.avoscloud.AVQuery;
//import com.avos.avoscloud.FindCallback;
//import com.mango.R;
//import com.mango.controller.Controller_skipPage;
//import com.mango.entity.entity_server.UserPass;
//import com.mango.mylibrary.base.BaseFragment;
//import com.mango.mylibrary.util.Util_app;
//import com.mango.mylibrary.util.Util_collection;
//
//import java.util.List;
//
///**
// *
// */
//
//public class SplashFragment extends BaseFragment {
//
//    public static SplashFragment newInstance() {
//        Bundle args = new Bundle();
//        SplashFragment fragment = new SplashFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//
//    @Override
//    public int getLayoutId() {
//        return R.layout.fragment_splash;
//    }
//
//    @Override
//    public void initViews(View parentView) {
//    }
//
//    @Override
//    public void initData(Bundle savedInstanceState) {
//        super.initData(savedInstanceState);
//        checkUser();
//    }
//
//
//    private void next() {
//        Controller_skipPage.toMain(getActivity());
//        getActivity().finish();
//    }
//
//    @Override
//    public void setViews(View parentView) {
//    }
//}
