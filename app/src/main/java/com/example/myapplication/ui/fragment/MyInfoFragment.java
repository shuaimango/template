package com.example.myapplication.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.myapplication.R;
import com.example.myapplication.entity.User;
import com.example.myapplication.util.ImageLoader;
import com.example.myapplication.util.Util_skipPage;
import com.example.mylibrary.base.BaseFragment;
import com.example.mylibrary.util.Util_Spannable;
import com.example.mylibrary.util.Util_layout;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 我的
 */
public class MyInfoFragment extends BaseFragment {
    private ImageView iv_avatar;
    TextView tv_nickname;
    private User user;
    private List<LocalMedia> selectImgs = new ArrayList<>();

    private void chooseImg() {
        selectImgs.clear();
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .selectionMedia(selectImgs)
                .selectionMode(PictureConfig.SINGLE)
                .compress(true)
                .enableCrop(true)
                .circleDimmedLayer(true)// 是否圆形裁剪
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择
                    selectImgs = PictureSelector.obtainMultipleResult(data);
                    final LocalMedia localMedia = selectImgs.get(0);
                    if (localMedia != null) {
                        String path = localMedia.getCompressPath();
                        if (TextUtils.isEmpty(path)) {
                            path = localMedia.getCutPath();
                            if (TextUtils.isEmpty(path)) {
                                path = localMedia.getPath();
                                if (TextUtils.isEmpty(path)) {
                                    ToastUtils.showShort("图片上传不了");
                                    return;
                                }
                            }
                        }
                        File file = new File(path);
                        if (file.length() > 1100000) {
                            ToastUtils.showShort("图片要小于1M");
                            return;
                        }
                        ImageLoader.displayCircleImg( iv_avatar,path);
                        ModifynickNameFragment.saveUser("",path);
//                        HttpParams params = new HttpParams();
//                        params.put("face", file);
//                        OkGo.<String>post(UrlHelper.packageUrl(UrlHelper.upface))
//                                .params(params)
//                                .execute(new BaseStringCallback() {
//                                    @Override
//                                    public void onSuccess(JSONObject jsonObject) {
//                                        super.onSuccess(jsonObject);
////                                        String face = jsonObject.getString("face");
////                                        ImageLoader.getInstance()
////                                                .displayCircleImg(getActivity(), face, iv_head);
//                                    }
//
//                                });
                    }
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            //头像
            case R.id.avatar:
                chooseImg();
                break;
            //昵称
            case R.id.nickname:
                Util_skipPage.startMyFragmentActivity(getActivity(),ModifynickNameFragment.class.getName());
                break;
        }
    }




    @Override
    public int getLayoutId() {
        return R.layout.fragment_myinfo;
    }

    @Override
    public void initViews(View parentView) {
        iv_avatar = parentView.findViewById(R.id.iv_avatar);
    }

    @Override
    public void setViews(View parentView) {
        titlebar.setTitle("修改个人资料");
        Util_layout.setItemChoose(parentView.findViewById(R.id.avatar), Util_Spannable.setTextForeground(
                "头像", getResources().getColor(R.color.black1)),null, this);
        tv_nickname = Util_layout.setItemChoose(parentView.findViewById(R.id.nickname), Util_Spannable.setTextForeground(
                "昵称", getResources().getColor(R.color.black1)),null, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        String userStr = SPStaticUtils.getString(MeFragment.USER);
        if(!TextUtils.isEmpty(userStr)){
            user=JSON.parseObject(userStr, User.class);
            ImageLoader.displayCircleImg( iv_avatar, user.avatarUrl);
            tv_nickname.setText(user.nickname);
        }
    }
}
