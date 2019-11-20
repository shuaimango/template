package com.example.myapplication.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ToastUtils;
import com.example.myapplication.R;
import com.example.myapplication.constant.ServerUrl;
import com.example.myapplication.ui.HomeActivity;
import com.example.mylibrary.base.BaseFragment;
import com.example.mylibrary.base.BaseStringCallback;
import com.example.mylibrary.util.VerifyCodeTimer;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;

import java.util.regex.Pattern;

/**
 *
 */
public class LoginPhoneFragment extends BaseFragment {
    public static final String FirstLogin = "FirstLogin";
    private EditText editAccount;
    private EditText authCodeEt;
    private TextView getAuthCodeBtn, btLogin;
    boolean isFirstLogin;
    private VerifyCodeTimer verifyCodeTimer;

    public static LoginPhoneFragment newInstance(Bundle args) {
        LoginPhoneFragment fragment = new LoginPhoneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.getAuthCodeBtn:
                if (checkPhone()) {
                    sendCode();
                }
                break;
            case R.id.reg_bt:
                startActivity(new Intent(getActivity(), HomeActivity.class));
                if (checkPhone()) {
                    if (TextUtils.isEmpty(authCodeEt.getText())) {
                        ToastUtils.showShort("请输入验证码");
                        return;
                    }
                    doLogin(editAccount.getText().toString(), "", authCodeEt.getText().toString());
                }
                break;
        }
    }

    private void doLogin(String account, String pwd, String code) {
        HttpParams params = new HttpParams();
        params.put("account", account);
        params.put("code", code);
        params.put("ry", "1");
        OkGo.<String>post(ServerUrl.login)
                .params(params)
                .execute(new BaseStringCallback() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
//                        Util_sp.putString("phone", account);
//                        LoginUserEntity userinfo = JSON.parseObject(jsonObject.getString("userinfo"), LoginUserEntity.class);
//                        loginSuccess(LoginActivity.this,userinfo);
//                        if (isFirstLogin) {
//                            Intent intent = new Intent(LoginActivity.this, AULiveHomeActivity.class);
//                            startActivity(intent);
//                        }
//                        finish();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    private void sendCode() {
        HttpParams params = new HttpParams();
//        params.put("account", account);
//        params.put("code", code);
        OkGo.<String>post(ServerUrl.send_code)
                .params(params)
                .execute(new BaseStringCallback() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        if (verifyCodeTimer == null) {
                            verifyCodeTimer = new VerifyCodeTimer(getAuthCodeBtn, 60000, 1000);
                            verifyCodeTimer.setWordingCallback(new VerifyCodeTimer.WordingCallback() {
                                @Override
                                public String getCountdownWording(long millisUntilFinished) {
                                    return millisUntilFinished / 1000 + "s";
                                }

                                @Override
                                public String getAfterCountdownWoring() {
                                    getAuthCodeBtn.setEnabled(true);
                                    return getResources().getString(R.string.obtain_verif_code);
                                }
                            });
                        }
                        verifyCodeTimer.start();
                        getAuthCodeBtn.setEnabled(false);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    private boolean checkPhone() {
        if (editAccount.getText() == null || editAccount.getText().equals("")) {
            ToastUtils.showShort("请输入手机号");
            editAccount.requestFocus();
            return false;
        } else if (!matchPhone(editAccount.getText().toString())) {
            ToastUtils.showShort("请输入正确的手机号");
            editAccount.requestFocus();
            return false;
        }
        return true;
    }

    protected boolean matchPhone(String text) {
        if (Pattern.compile("(\\d{11})|(\\+\\d{3,})").matcher(text).matches()) {
            return true;
        }
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_login_phone;
    }

    @Override
    public void initViews(View parentView) {
        editAccount = parentView.findViewById(R.id.phoneEt);
        getAuthCodeBtn = parentView.findViewById(R.id.getAuthCodeBtn);
        authCodeEt = parentView.findViewById(R.id.authCodeEt);
        btLogin = parentView.findViewById(R.id.reg_bt);
    }

    @Override
    public void setViews(View parentView) {
        if (extras != null) {
            isFirstLogin = extras.getBoolean(FirstLogin);
        }
        titlebar.setTitle("手机登录");
        editAccount.requestFocus();
        getAuthCodeBtn.setOnClickListener(this);
        btLogin.setOnClickListener(this);
    }
}
