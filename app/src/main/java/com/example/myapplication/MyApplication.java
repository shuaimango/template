package com.example.myapplication;

import android.app.ActivityManager;
import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.DisplayMetrics;


import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.example.myapplication.entity.User;
import com.example.myapplication.ui.fragment.MeFragment;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;


public class MyApplication extends MultiDexApplication {
    public static boolean isFirstEnter;
    public static User user;
    public static MyApplication mContext = null;
    public static int mScreenWidthPx, mScreenHeightPx;
    public static float mScreenWidthDp, mScreenHeightDp;
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
    /**
     * 如果 APP 包含远程 service，该 APP 的 Application 的 onCreate 会多次调用
     */
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            if (isMainProcess()) {
                isFirstEnter = true;
                mContext = this;
                DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
                mScreenWidthPx = dm.widthPixels;
                mScreenHeightPx = dm.heightPixels;
                mScreenWidthDp = ConvertUtils.dp2px(mScreenWidthPx);
                mScreenHeightDp = ConvertUtils.dp2px(mScreenHeightPx);
                initPush();
                initHttp();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User getUserInfo() {
        if (user == null) {
            String userStr = SPStaticUtils.getString(MeFragment.USER);
            if(TextUtils.isEmpty(userStr)){
                user=new User();
                user.nickname="无名氏";
            }else{
                user= JSON.parseObject(userStr,User.class);
            }
        }
        return user;
    }

    public static void initPush() {
        // 开启推送服务
//        XGPuConfig.enableDebug(mContext, AppConstant.isDebug); // 开启logcat输出，方便debug，发布时请关闭
//        XGIOperateCallback callback = new XGIOperateCallback() {
//            @Override
//            public void onSuccess(Object data, int flag) {
//                Log.e("TPu", "注册成功，设备token为：" + data);
//            }
//
//            @Override
//            public void onFail(Object data, int errCode, String msg) {
//                Log.e("TPu", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
//            }
//        };
//        XGPuManager.registerPu(mContext, callback);
//                UserInfo userInfo = Interactor_user_local.getUserInfo();
//        if(userInfo==null|| TextUtils.isEmpty(userInfo.u_mobile)){
//            XGPuManager.registerPu(mContext, callback);
//        }else{
//            XGPuManager.registerPu(mContext,userInfo.u_mobile,callback);
//        }
//        XGCustomPuNotificationBuilder build = new XGCustomPuNotificationBuilder();
//        build.setSound(RingtoneManager.getActualDefaultRingtoneUri(mContext, RingtoneManager.TYPE_ALARM)) // 设置声音
//                // setSound( Uri.parse("android.resource://" + getPackageName()// + "/" + R.raw.wind)) 设定Raw下指定声音文件
//                .setDefaults(Notification.DEFAULT_VIBRATE); // 振动
//        XGPuManager.setDefaultNotificationBuilder(mContext, build);  //这个是替换默认的通知，build是上面的那段代码的，这样通知就是使用我们自定义的形式了。
    }

    private void initHttp() {
        //使用OkGo的拦截器
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("meee");
        //日志的打印范围
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //在logcat中的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        //默认是Debug日志类型
        builder.addInterceptor(loggingInterceptor);
        //设置请求超时时间,默认60秒
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);      //读取超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);     //写入超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);   //连接超时时间
        //设置全局请求参数,支持中文
//      HttpParams params = new HttpParams();
//      MobileConfig config=MobileConfig.getMobileConfig(AULiveApplication.mContext);
//      params.put("app_channel", config.getAppMetaData(AULiveApplication.mContext, "UMENG_CHANNEL"));
//      params.put("app_version", config.getPkgVerCode());
//      params.put("system_name", "android");
//      if(AULiveApplication.getUserInfo()!=null) {
//         params.put("uid", AULiveApplication.getUserInfo().getUid());
//      }
        //      //okhttp默认不保存cookes/session信息,需要自己的设置
        //只能用一套cookie,要么用原网络框架cookie，要么用okgo网络框架cookie
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));            //使用sp保持cookie，如果cookie不过期，则一直有效
//        builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));              //使用数据库保持cookie，如果cookie不过期，则一直有效
//      builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));            //使用内存保存cookie,退出后失效
        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build())//不设置则使用默认
                .setCacheMode(CacheMode.NO_CACHE)//设置缓存模式
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)//设置缓存时间,默认永不过期
//              .addCommonParams(params)
                .setRetryCount(3);//请求超时重连次数,默认3次
//      OkGo.getInstance().addCommonHeaders(headers);
    }
    private boolean isMainProcess() {
        return getPackageName().equals(getAppProcessName(this));
    }
    /**
     * @return 返回包名
     */
    public static String getAppProcessName(Context context) {
        //当前应用pid
        int pid = android.os.Process.myPid();
        //任务管理类
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //遍历所有应用
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == pid)//得到当前应用
                return info.processName;//返回包名
        }
        return "";
    }
}