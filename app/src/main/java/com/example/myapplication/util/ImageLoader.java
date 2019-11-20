package com.example.myapplication.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.MyApplication;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 *
 */
public class ImageLoader {

    public static void displayImage(String imgUrl, ImageView avatar) {
        displayImage(MyApplication.mInstance, imgUrl, avatar);
    }

    public static void displayImage(Context context, String imgUrl, ImageView avatar) {
        displayImage(context, imgUrl, avatar, null);
    }

    public static void displayImage(Context context, String imgUrl, ImageView avatar, RequestOptions options) {
        if (avatar == null) {
            return;
        }
        if (TextUtils.isEmpty(imgUrl)) {
//            Glide.with(context==null?MyApplication.mInstance:context).load(R.drawable.appicon)
//                    .into(avatar);
            return;
        }
        if (options == null) {
            Glide.with(context == null ? MyApplication.mInstance : context)
                    .load(imgUrl)
                    .into(avatar);
        } else {
            Glide.with(context == null ? MyApplication.mInstance : context)
                    .load(imgUrl)
                    .apply(options)
                    .into(avatar);
        }
    }

    public static void displayCornersImage(Context context, String imgUrl, ImageView avatar, int corner) {
        RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation
                (ConvertUtils.dp2px(corner), 0, RoundedCornersTransformation.CornerType.ALL);
        MultiTransformation<Bitmap> mation = new MultiTransformation<>
                //Glide设置圆角图片后设置ImageVIew的scanType="centerCrop"无效解决办法,将new CenterCrop()添加至此
                (new CenterCrop(), roundedCornersTransformation);
        RequestOptions options = bitmapTransform(mation);
        displayImage(context, imgUrl, avatar, options);
    }

    public static void displayBlurImage(String imgUrl, ImageView avatar, int corner) {
        RequestOptions options = bitmapTransform(new BlurTransformation(corner));
        displayImage(MyApplication.mInstance, imgUrl, avatar, options);
    }

    public static void displayCircleImg(ImageView avatar,String imgUrl) {
        RequestOptions options = bitmapTransform(new CircleCrop());
        displayImage(MyApplication.mInstance, imgUrl, avatar, options);
    }
}
