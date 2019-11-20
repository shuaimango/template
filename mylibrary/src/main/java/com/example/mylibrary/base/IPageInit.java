package com.example.mylibrary.base;

import android.os.Bundle;
import android.view.View;

/**
 */

public interface IPageInit {
    int getLayoutId();

    void initViews(View parentView);

    void initData(Bundle savedInstanceState);

    void loadData();

    void setViews(View parentView);
}
