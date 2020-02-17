package com.example.myapplication.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 */
public class ImageLoader {

    public static void displayImage(String imgUrl, ImageView avatar ) {
        displayImage(MyApplication.mContext,imgUrl,avatar);
    }
    public static void displayImage(Context context, String imgUrl, ImageView avatar ) {
        displayImage(context,imgUrl,avatar,null);
    }

    public static void displayImage(Context context, String imgUrl, ImageView avatar , RequestOptions options ) {
        displayImage(context,imgUrl,avatar,options,false);

    }
    public static void displayImage(Context context, String imgUrl, ImageView avatar , RequestOptions options ,boolean isLocalImg) {
        if(avatar==null){
            return;
        }
        if(TextUtils.isEmpty(imgUrl)){
            Glide.with(context==null? MyApplication.mContext :context).load(R.mipmap.app_icon)
                    .into(avatar);
            return;
        }
        if(options==null){
            Glide.with(context==null?MyApplication.mContext:context)
                    .load( imgUrl)
                    .into(avatar);
        }else{
            Glide.with(context==null?MyApplication.mContext:context)
                    .load( imgUrl)
                    .apply(options)
                    .into(avatar);
        }
    }

    public static void displayCornersImage(Context context, String imgUrl, ImageView avatar ,int corner) {
        RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation
                (ConvertUtils.dp2px(corner), 0, RoundedCornersTransformation.CornerType.ALL);
        MultiTransformation<Bitmap> mation = new MultiTransformation<>
                //Glide设置圆角图片后设置ImageVIew的scanType="centerCrop"无效解决办法,将new CenterCrop()添加至此
                (new CenterCrop(), roundedCornersTransformation);
        RequestOptions options = bitmapTransform(mation);
        displayImage(context,imgUrl,avatar,options);
    }

    public static void displayBlurImage( String imgUrl, ImageView avatar ,int corner) {
        RequestOptions options = bitmapTransform(new BlurTransformation(corner));
        displayImage(MyApplication.mContext,imgUrl,avatar,options);
    }

    public static void displayCircleImg( String imgUrl, ImageView avatar) {
        RequestOptions options = bitmapTransform(new CircleCrop());
        displayImage(MyApplication.mContext,imgUrl,avatar,options);
    }
    public static void displayCircleImg(String imgUrl, ImageView avatar,boolean isLocalImg) {
        RequestOptions options = bitmapTransform(new CircleCrop());
        displayImage(MyApplication.mContext,imgUrl,avatar,options,isLocalImg);
    }
    public static void displayCornerBorderImg( String imgUrl, ImageView avatar,int corner) {
        displayCornerBorderImg(imgUrl,avatar,corner,1,"#DEDEDE");
    }
    public static void displayCornerBorderImg( String imgUrl, ImageView avatar,int corner,int borderWidth,String borderColor) {
//        RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation(corner, borderWidth);
        GlideCircleBorderTransform roundedCornersTransformation = new GlideCircleBorderTransform
                (corner, borderColor, borderWidth);
        MultiTransformation<Bitmap> mation = new MultiTransformation<>
                //Glide设置圆角图片后设置ImageVIew的scanType="centerCrop"无效解决办法,将new CenterCrop()添加至此
                (new CenterCrop(), roundedCornersTransformation);
        RequestOptions options = bitmapTransform(mation);
        displayImage(MyApplication.mContext,imgUrl,avatar,options);
    }

    /**
     */
    public static void displayImageFitWidth( final String imageUrl, final ImageView imageView) {
        Glide.with(MyApplication.mContext).load(imageUrl).skipMemoryCache(true).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                /*
                 * 在此处填写具体载入逻辑*/

                /*
                判断imageView对象是否为空
                 */
                if (imageView == null){
                    return false;
                }
                /*
                判断imageView的填充方式,如果不是fitxy的填充方式 设置其填充方式
                 */
                if(imageView.getScaleType()!=ImageView.ScaleType.FIT_XY){
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                }
                /*
                进行宽度为matchparent时的适应imageView的高度计算
                 */
                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                float scale = (float) vw /(float) resource.getIntrinsicWidth();
                int vh = Math.round(resource.getIntrinsicHeight() * scale);
                params.height = vh + imageView.getPaddingTop()+imageView.getPaddingBottom();
                imageView.setLayoutParams(params);
                return false;
            }
        }).into(imageView);
    }
}
