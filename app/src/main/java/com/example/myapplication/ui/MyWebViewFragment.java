package com.example.myapplication.ui;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ArrayUtils;
import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.myapplication.entity.Manhua;
import com.example.myapplication.ui.fragment.ManhuaTabFragment;
import com.example.myapplication.ui.fragment.MyManhuCollectFragment;
import com.example.mylibrary.R;
import com.example.mylibrary.base.BaseFragment;
import com.example.mylibrary.ui.WebViewTitleFragment;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.store.CookieStore;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;

public class MyWebViewFragment extends WebViewTitleFragment {


    @Override
    public void setViews(View parentView) {
        super.setViews(parentView);
        webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 当开启新的页面的时候用webview来进行处理而不是用系统自带的浏览器处理
                //自定义处理
                Uri uri = Uri.parse(url);
                if (url.startsWith("manhua://collect")) {
//                    ToastUtils.showShort("收藏成功");
                    String id = uri.getQueryParameter("id");
                    saveManhua(MyManhuCollectFragment.MyManhuCollect,id);
                    return true;
                }
                //自定义协议
                if ("shuiyue".equals(uri.getScheme())) {
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                    getActivity().overridePendingTransition(0, 0);
                    return true;
                }
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // super.onReceivedSslError(view, handler, error);
                // handler.cancel(); // 默认的处理方式，WebView变成空白页
                handler.proceed();// 接受证书
                // handleMessage(Message msg); 其他处理
            }
        });
    }

    public static void saveManhua(String key,String id) {
        String myManhuaCollects = SPStaticUtils.getString(key);
        List<Manhua> list2=null;
        if(TextUtils.isEmpty(myManhuaCollects)){
            list2 = new ArrayList<>();
        }else {
            try {
                list2 = JSON.parseArray(myManhuaCollects, Manhua.class);
            }catch (Exception e){
                e.printStackTrace();
            }
            if (CollectionUtils.isEmpty(list2)) {
                list2 = new ArrayList<>();
            }
        }
        Manhua manhua = ManhuaTabFragment.getMap().get(id);
        if (!list2.contains(manhua)) {
            list2.add(manhua);
            SPStaticUtils.put(key, JSON.toJSONString(list2));
        }
    }
}