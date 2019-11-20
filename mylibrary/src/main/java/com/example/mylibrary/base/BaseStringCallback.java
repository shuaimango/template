package com.example.mylibrary.base;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class BaseStringCallback extends StringCallback {
    private boolean isShowSuccessMessage;

    public BaseStringCallback() {
    }

    public BaseStringCallback(boolean isShowSuccessMessage) {
        this.isShowSuccessMessage = isShowSuccessMessage;
    }

    @Override
    public void onSuccess(Response<String> response) {
        try {
            String body = response.body();
            if (!TextUtils.isEmpty(body)) {
                JSONObject jsonObject = JSON.parseObject(body);
                switch (jsonObject.getIntValue("stat")) {
                    case 200:
                        onSuccess(jsonObject);
                        if(isShowSuccessMessage)
//                            Utils.showMessage(jsonObject.getString("msg"));
                        break;
                    default:
                        onDefaultHandler(jsonObject);
//                        Utils.showMessage(jsonObject.getString("msg"));
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSuccess(JSONObject jsonObject) {

    }
    public void onDefaultHandler(JSONObject jsonObject) {

    }
}
