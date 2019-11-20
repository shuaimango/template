package com.example.myapplication.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;


import com.example.myapplication.ui.MyFragmentActivity;
import com.example.mylibrary.base.BaseFragment;
import com.example.mylibrary.util.Util_input;


public class Util_skipPage {
    /**
     * * 跳转至包含fragment的通用Activity，这样就不用重复定义Activity以及注册androidmanifest
     *
     * @param context
     * @param bundle
     * @param title
     * @param toFragmentClassName 全路径类名  AddStyleCircleFragment.class.getName()
     */
    public static void startMyFragmentActivity(Context context, Bundle bundle, String title, Class activityClass, String toFragmentClassName) {
        if (TextUtils.isEmpty(toFragmentClassName)) {
            return;
        }
        Intent intent = Util_skipPage.getRightIntent(context);
        intent.setClass(context, activityClass);
        if (bundle == null)
            bundle = new Bundle();
        if (!TextUtils.isEmpty(title))
            bundle.putString(BaseFragment.TITLE, title);
        bundle.putString(MyFragmentActivity.CLASSNAME, toFragmentClassName);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void startMyFragmentActivity(Context context, Bundle bundle, String title, String toFragmentClassName) {
        startMyFragmentActivity(context, bundle, title, MyFragmentActivity.class, toFragmentClassName);
    }

    public static void startMyFragmentActivity(Context context, String title, String toFragmentClassName) {
        startMyFragmentActivity(context, null, title, toFragmentClassName);
    }

    public static void startMyFragmentActivity(Context context, Bundle bundle, String toFragmentClassName
    ) {
        startMyFragmentActivity(context, bundle, "", toFragmentClassName);
    }

    public static void startMyFragmentActivity(Context context, String toFragmentClassName) {
        startMyFragmentActivity(context, "", toFragmentClassName);
    }



    public static void webViewBack(AppCompatActivity activity, WebView webView) {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            activity.finish();
        }
    }

    public static void toUrl(Context activity, String url) {
        Intent intent =getRightIntent(activity);
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        Log.i("toUrl", url);
//        uc浏览器"："com.uc.browser", "com.uc.browser.ActivityUpdate“
//　　opera："com.opera.mini.android", "com.opera.mini.android.Browser"
//　　qq浏览器："com.tencent.mtt", "com.tencent.mtt.MainActivity"
        try {
            intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            activity.startActivity(intent);
        }
    }

    public static void fragmentBack(AppCompatActivity activity) {
        try {
            Util_input.hideKeyboardFromActivity(activity);
            if (activity.getSupportFragmentManager().getBackStackEntryCount() > 0) {
                activity.getSupportFragmentManager().popBackStack(null, 0);
            } else {
                activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static final void startActivity(Context context, Class className) {
        startActivity(context, className, null);
    }

    /**
     */
    public static final void startActivity(Context context, Class className, Bundle bundle) {
        Intent intent = Util_skipPage.getRightIntent(context);
        intent.setClass(context, className);
        if (bundle != null)
            intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     *
     */
    public static final void startActivityForResult(AppCompatActivity activity, Class className, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(activity, className);
        if (bundle != null)
            intent.putExtras(bundle);
        activity.startActivityForResult(intent, requestCode);
    }

    public static Intent getRightIntent(Context context) {
        Intent intent = new Intent();
        if (!(context instanceof AppCompatActivity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        return intent;
    }

    public static void toCustomProtocolActivity(Context context, Uri uri) {
        Intent intent = getRightIntent(context);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(uri);
        context.startActivity(intent);
    }

    public static void toCustomActivity(Context context, Class<? extends AppCompatActivity> cls) {
        toCustomActivity(context, cls, null);
    }

    public static void toCustomActivity(Context context, Class<? extends AppCompatActivity> cls, Bundle bundle) {
        toCustomActivity(context, cls, bundle, 0);
    }

    public static void toCustomActivity(Context context, Class<? extends AppCompatActivity> cls, Bundle bundle, int flags) {
        Intent intent = getRightIntent(context);
        intent.setClass(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (flags > 0) {
            intent.addFlags(flags);
        }
        context.startActivity(intent);
    }
}
