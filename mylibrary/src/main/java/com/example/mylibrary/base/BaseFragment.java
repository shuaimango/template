package com.example.mylibrary.base;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylibrary.R;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import org.greenrobot.eventbus.EventBus;

import me.yokeyword.fragmentation.SupportFragment;


public abstract class BaseFragment extends SupportFragment implements IPageInit, View.OnClickListener {
    public static final String EXTRA_DATA_STRING = "dataString";
    public static final String TITLE = "title";
    public String TAG;
    public Bundle extras;
    protected TitleBar titlebar;


    public boolean isRegisterEventBus() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.back:
//                getActivity().onBackPressed();
////                Util_skipPage.fragmentBack(getActivity());
//                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (EventBus.getDefault().isRegistered(this) && isRegisterEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    public void postEventBus(Object event) {
        EventBus.getDefault().post(event);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        TAG = this.getClass().getSimpleName();
        extras = getArguments();
        if (!EventBus.getDefault().isRegistered(this) && isRegisterEventBus()) {
            EventBus.getDefault().register(this);
        }
        View view = inflater.inflate(getLayoutId(), container, false);
        titlebar = view.findViewById(R.id.titlebar);
        initViews(view);
        initTitlebar(titlebar);
        initData(savedInstanceState);
        return view;
    }

    private void initTitlebar(TitleBar titlebar) {
        if(titlebar==null){
            return;
        }
        if (extras != null) {
            String title = extras.getString(TITLE);
            if (!TextUtils.isEmpty(title)) {
                titlebar.setTitle(title);
            }
        }
        titlebar.setLeftIcon(R.mipmap.icon_back);
        titlebar.setTitleColor(getActivity().getResources().getColor(R.color.black1));
        titlebar.setRightColor(getActivity().getResources().getColor(R.color.black1));
        titlebar.setOnTitleBarListener(new OnTitleBarListener() {

            @Override
            public void onLeftClick(View v) {
                getActivity().onBackPressed();
            }

            @Override
            public void onTitleClick(View v) {
            }

            @Override
            public void onRightClick(View v) {
            }
        });
    }

    @Override
    public void loadData() {
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setViews(view);
        loadData();
    }

    protected Uri getData() {
        return Uri.parse(getDataString());
    }

    protected String getDataString() {
        if (extras != null) {
            return extras.getString(EXTRA_DATA_STRING);
        }
        return null;
    }

}
