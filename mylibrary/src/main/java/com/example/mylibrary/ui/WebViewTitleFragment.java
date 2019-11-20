package com.example.mylibrary.ui;

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

import com.blankj.utilcode.util.ToastUtils;
import com.example.mylibrary.R;
import com.example.mylibrary.base.BaseFragment;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.store.CookieStore;

import java.io.File;
import java.util.List;

import okhttp3.Cookie;

public class WebViewTitleFragment extends BaseFragment {

    protected WebView webview;
    public final static String URL = "URL";
    public final static String TYPE = "TYPE";
    private String root_url;

    public static WebViewTitleFragment newInstance(Bundle bdl) {
        WebViewTitleFragment f = new WebViewTitleFragment();
        f.setArguments(bdl);
        return f;
    }

    @Override
    public int getLayoutId() {
        return R.layout.webview_title;
    }

    @Override
    public void initViews(View parentView) {
        webview = parentView.findViewById(R.id.webview);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        root_url = getArguments().getString(URL);
        CookieStore cookieStore = OkGo.getInstance().getCookieJar().getCookieStore();
        List<Cookie> allCookie = cookieStore.getAllCookie();
        syncCookie(root_url, allCookie);
//      String mCookie =
//              SharedPreferenceTool.getInstance().getString(SharedPreferenceTool.COOKIE_KEY, "");
//      WebViewFileUtils.syncCookie(root_url,mCookie, getContext());
//      Trace.d("webview cookie:"+mCookie);
    }

    @Override
    public void setViews(View parentView) {
        //设置webview背景色透明，避免默认白色不搭
        webview.setBackgroundColor(0);
        WebSettings webSettings = webview.getSettings();
        //webview默认缓存页面
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("gb2312");
        webSettings.setAllowFileAccess(true);
        webSettings.setDomStorageEnabled(true);
        // webview.setOnClickListener(this);
        webview.loadUrl(root_url);
        webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 当开启新的页面的时候用webview来进行处理而不是用系统自带的浏览器处理
                //自定义处理
                Uri uri = Uri.parse(url);
                if (url.startsWith("manhua://collect")) {
                    ToastUtils.showShort("收藏成功");
                    String id = uri.getQueryParameter("id");
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


    public static boolean syncCookie(String url, List<Cookie> cookies) {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        for (Cookie cookie : cookies) {
            String cookieUrl = cookie.domain();
            String cookieValue = cookie.name() + "=" + cookie.value()
                    + "; path=" + cookie.path()
                    + "; domain=" + cookie.domain();
            cookieManager.setCookie(cookieUrl, cookieValue);
        }
        String newCookie = cookieManager.getCookie(url);
        return TextUtils.isEmpty(newCookie) ? false : true;
    }

}