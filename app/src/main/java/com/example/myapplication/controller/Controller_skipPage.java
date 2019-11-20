package com.example.myapplication.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.ui.fragment.LoginPhoneFragment;
import com.example.myapplication.util.Util_skipPage;

public class Controller_skipPage {

    public static void toLogin(Context context) {
        Intent intent = Util_skipPage.getRightIntent(context);
        Bundle bundle = new Bundle();
        context.startActivity(intent);
        Util_skipPage.startMyFragmentActivity(context, LoginPhoneFragment.class.getName());
    }


}
