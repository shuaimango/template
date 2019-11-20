package com.example.myapplication.constant;


import android.os.Environment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppConstant {
    public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(5);
    /**
     * 服务端接口地址
     */
    public static boolean isDebug = false;// false上线模式;true开发模式
    public static final String host = isDebug ? "://see-devcdn.seecsee.com"
            : "://seecsee.com";// 主机
    public static final String APPDIR_PATH = getRootFilePath() + "see/";
    public static final String APPDIR_IMG = APPDIR_PATH + "IMG/";

    public static boolean hasSDCard() {
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

    public static String getRootFilePath() {
        if (hasSDCard()) {
            return Environment.getExternalStorageDirectory().toString() + "/";// filePath:/sdcard/
        } else {
            return Environment.getDataDirectory().getAbsolutePath() + "/friendData/"; // filePath:
        }
    }
}
