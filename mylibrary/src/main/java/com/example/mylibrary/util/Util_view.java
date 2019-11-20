package com.example.mylibrary.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileOutputStream;
import java.util.Timer;
import java.util.TimerTask;

/**
 */
public class Util_view {
    public static void setOSPDIN_TTF(Context context,TextView tv_num, String text) {
        tv_num.setText(text);
        Typeface typeface= Typeface.createFromAsset(context.getAssets(),"OSP-DIN.ttf");
        tv_num.setTypeface(typeface);
    }
    /*
    *文字折叠
     */
    public static void setEllipsisText(final TextView tv_profile,final String content,final int arrow_down_blue,final int arrow_up_blue) {
        tv_profile.setText(content);
        tv_profile.setEllipsize(TextUtils.TruncateAt.END);
        ViewTreeObserver vto2 = tv_profile.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int ellipsisCount = tv_profile.getLayout().getEllipsisCount(tv_profile.getLineCount() - 1);
                //ellipsisCount>0说明没有显示全部，存在省略部分。
                if (ellipsisCount > 0) {
                    //展示全部，按钮设置为点击收起。
                    tv_profile.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, arrow_down_blue);
                    if (!tv_profile.hasOnClickListeners()) {
                        tv_profile.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (tv_profile.getMaxLines() == 3) {
                                    tv_profile.setMaxLines(100);
                                    tv_profile.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, arrow_up_blue);
                                } else {
                                    tv_profile.setMaxLines(3);
                                    tv_profile.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, arrow_down_blue);
                                }
                                tv_profile.setText(content);
                            }
                        });
                    }
                } else {
                    int drawRes = tv_profile.getMaxLines() > 3 ? arrow_up_blue : 0;
                    tv_profile.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, drawRes);
                }
//                TextPaint mTextPaint = tv1.getPaint();
//                mTextPaint.setTextSize(tv1.getTextSize());
//                int mTextViewWidth = (int) mTextPaint.measureText("《三体》是刘慈欣创作的系列长篇");
//                tv1.setText("《三体》是刘慈欣创作的系列长篇");
//                if (mTextViewWidth > tv1.getWidth()) {//超出一行
//                    tv2.setText("超出一行");
//                } else {
//                    tv2.setText("未超出一行");
//                }
            }
        });
    }
    public static AppCompatActivity getActivityFromView(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof AppCompatActivity) {
                return (AppCompatActivity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    public static void toast(Context activity, String str) {
        Toast.makeText(activity,str, Toast.LENGTH_LONG).show();
    }

    public static void showMyToast(Context activity, String str, final int showTime) {
        final Toast toast= Toast.makeText(activity,str, Toast.LENGTH_LONG);
        final Timer timer =new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        },0,3000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, showTime );
    }

    //截取屏幕的方法
    public static File screenshot(View dView) {
        File file=null;
        // 获取屏幕
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        Bitmap bmp = dView.getDrawingCache();
        if (bmp != null)
        {
            try {
                // 获取内置SD卡路径
                String sdCardPath = Environment.getExternalStorageDirectory().getPath();
                // 图片文件路径
                String imagePath = sdCardPath + File.separator + "screenshot.png";
                file = new File(imagePath);
                FileOutputStream os = new FileOutputStream(file);
                bmp.compress(Bitmap.CompressFormat.PNG, 100, os);
                os.flush();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }


}
